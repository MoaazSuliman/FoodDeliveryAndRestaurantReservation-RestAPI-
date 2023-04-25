package com.moaaz.resturant.controller;

import com.moaaz.resturant.model.Cart;
import com.moaaz.resturant.model.Food;
import com.moaaz.resturant.model.User;
import com.moaaz.resturant.service.CartService;
import com.moaaz.resturant.service.FoodService;
import com.moaaz.resturant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/carts")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;


    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable int userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(userService.getUserById(userId).getCart(), HttpStatus.OK);
    }

    @PostMapping("/addFood/{foodId}/toCart/{cartId}")
    public ResponseEntity<?> addFoodToCart(@PathVariable int foodId, @PathVariable int cartId) {
        Food food = foodService.getFoodById(foodId);
        Cart cart = cartService.getCartById(cartId);

        cart.addFood(food);
        return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteFood/{foodId}/fromCart/{cartId}")
    public ResponseEntity<?> deleteFoodFromCart(@PathVariable int foodId, @PathVariable int cartId) {
        Food food = foodService.getFoodById(foodId);

        Cart cart = cartService.getCartById(cartId);

        Cart newCart = cartService.deleteFoodFromCart(foodId, cart);
        return new ResponseEntity<>(cartService.updateCart(cart), HttpStatus.ACCEPTED);
    }


}
