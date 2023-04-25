package com.moaaz.resturant.service;

import com.moaaz.resturant.model.Cart;
import com.moaaz.resturant.model.User;
import com.moaaz.resturant.repository.PersonRepository;
import com.moaaz.resturant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        user.setRole("USER");
        // Dependency Injection
        user.setCart(new Cart());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        checkIfUserIsExistOrThrowException(user.getId());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int userId) {
        checkIfUserIsExistOrThrowException(userId);
        userRepository.deleteById(userId);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public User getUserById(int userId) {

        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("There Are No User With Id = " + userId)
        );
    }

    public void checkIfUserIsExistOrThrowException(int userId) {
        getUserById(userId);
    }


    public List<User> getAllUsersThatAreHaveAOrder() {
        ArrayList<User> usersThatHaveOrders = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getOrders() != null && user.getOrders().size() > 0) usersThatHaveOrders.add(user);
        }
        return usersThatHaveOrders;
    }
}
