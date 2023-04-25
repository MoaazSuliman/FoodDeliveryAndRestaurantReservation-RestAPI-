package com.moaaz.resturant.controller;

import com.moaaz.resturant.model.Category;
import com.moaaz.resturant.model.Food;
import com.moaaz.resturant.service.CategoryService;
import com.moaaz.resturant.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @PostMapping("/add/category/{categoryId}")
    public ResponseEntity<?> addFood(@RequestBody Food food, @PathVariable int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        // DEPENDENCY Injection....
        food.setCategory(category);
        return new ResponseEntity<>(foodService.addFood(food), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{foodId}")
    public ResponseEntity<?> getFoodById(@PathVariable int foodId) {
        return new ResponseEntity<>(foodService.getFoodById(foodId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/category/{categoryId}")
    public ResponseEntity<?> updateFood(@RequestBody Food food, @PathVariable int categoryId) {
        food.setCategory(categoryService.getCategoryById(categoryId));
        return new ResponseEntity<>(foodService.updateFood(food), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        foodService.deleteFoodById(id);
        return new ResponseEntity<>("Deleted. ", HttpStatus.ACCEPTED);
    }
}
