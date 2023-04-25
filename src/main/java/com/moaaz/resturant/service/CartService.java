package com.moaaz.resturant.service;

import com.moaaz.resturant.model.Cart;
import com.moaaz.resturant.model.Food;
import com.moaaz.resturant.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartById(int id) {

        return cartRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("There Are No Cart With Id = " + id)
        );
    }

    public void checkIfCartIsExistOrThrowException(int cartId) {
        getCartById(cartId);
    }

    public double getTotalMoneyForThisCart(Cart cart) {
        checkIfCartIsExistOrThrowException(cart.getId());
        double totalMoney = 0;
        List<Food> foods = cart.getFoods();
        for (int i = 0; i < foods.size(); i++) {
            totalMoney += foods.get(i).getTotal();
        }
        return totalMoney;
    }

    public double getTotalMoneyForThisCartBeforeDiscount(Cart cart) {
        checkIfCartIsExistOrThrowException(cart.getId());
        double totalMoneyBeforeDiscount = 0;
        List<Food> foods = cart.getFoods();
        for (int i = 0; i < foods.size(); i++) {
            totalMoneyBeforeDiscount += foods.get(i).getPrice();
        }
        return totalMoneyBeforeDiscount;
    }

    public Cart deleteFoodFromCart(int foodId, Cart cart) {
        checkIfCartIsExistOrThrowException(cart.getId());
        List<Food> foods = cart.getFoods();
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getId() == foodId) {
                foods.remove(i);
                break;
            }
        }
        cart.setFoods(foods);
        return cart;
    }

    public void restartCart(Cart cart) {
        checkIfCartIsExistOrThrowException(cart.getId());
        cart.setFoods(null);
        updateCart(cart);
    }

}
