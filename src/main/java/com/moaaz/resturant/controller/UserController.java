package com.moaaz.resturant.controller;

import com.moaaz.resturant.model.User;
import com.moaaz.resturant.service.LoginService;
import com.moaaz.resturant.service.MailSenderService;
import com.moaaz.resturant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody User user) {
        /* Send Message To Email To Make Him Know He Is In Our DB Now */
        mailSenderService.sendMessage("Welcome In Our Klassy Restaurant... ", user.getEmail());
        return userService.addUser(user);
    }


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        if (user == null)
            return new ResponseEntity<>("There Are No user with id " + userId, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted Success. ", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllThatHaveOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsersThatHaveOrders() {
        return userService.getAllUsersThatAreHaveAOrder();
    }
}
