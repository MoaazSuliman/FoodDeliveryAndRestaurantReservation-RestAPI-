package com.moaaz.resturant.service;


import com.moaaz.resturant.model.Food;
import com.moaaz.resturant.model.Order;
import com.moaaz.resturant.model.User;
import com.moaaz.resturant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
    checkIfOrderIsExistOrThrowException(order.getId());
        orderRepository.save(order);
    }

    public void deleteOrderById(int id) {
        checkIfOrderIsExistOrThrowException(id);
        orderRepository.deleteById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public List<Food> getFoods(List<Food> foods) {
        ArrayList<Food> orderFoods = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = new Food(foods.get(i).getId(),
                    foods.get(i).getName(),
                    foods.get(i).getDetail(),
                    foods.get(i).getUrl(),
                    foods.get(i).getPrice(),
                    foods.get(i).getDiscount(),
                    foods.get(i).getTotal(),
                    foods.get(i).getCategory());
            orderFoods.add(food);
        }
        return orderFoods;
    }


    public User removeOrderFromUser(int order_id, User user) {
        List<Order> orders = user.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == order_id) {
                orders.remove(i);
                break;
            }
        }
        user.setOrders(orders);
        return user;
    }

    public double getAllTotalForOrders(List<Order> orders) {
        double total = 0;
        for (Order order :
                orders) {
            total += order.getTotal();
        }
        return total;
    }

    public void deleteListOfOrders(List<Order> orders) {
        orderRepository.deleteAll(orders);
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new NoSuchElementException("There Are No Order With Id = " + orderId)
        );
    }

    public void checkIfOrderIsExistOrThrowException(int orderId) {
        getOrderById(orderId);
    }
}
