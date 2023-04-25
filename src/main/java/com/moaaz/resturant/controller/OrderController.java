package com.moaaz.resturant.controller;

import com.moaaz.resturant.model.Cart;
import com.moaaz.resturant.model.Food;
import com.moaaz.resturant.model.Order;
import com.moaaz.resturant.model.User;
import com.moaaz.resturant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getOrdersForUser(@PathVariable int userId) {
        User user = userService.getUserById(userId);

        return new ResponseEntity<>(user.getOrders(), HttpStatus.OK);
    }

    @PostMapping("/add/user/{userId}")
    public ResponseEntity<?> addOrderForUser(@RequestBody Order order, @PathVariable int userId) {

        User user = userService.getUserById(userId);
        List<Food> listOfUsersFoodThatAreInHisCart = user.getCart().getFoods();
        order.setFoods(listOfUsersFoodThatAreInHisCart);
        user.getOrders().add(order);
        user.setCart(new Cart());
        System.out.println(order.toString());


        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

}
