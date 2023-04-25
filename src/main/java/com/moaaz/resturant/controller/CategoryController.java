package com.moaaz.resturant.controller;

import com.moaaz.resturant.model.Category;
import com.moaaz.resturant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/deleteById/{categoryId}")
    public ResponseEntity<?> deleteById(@PathVariable int categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>("Deleted ", HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {

        return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.ACCEPTED);
    }

}
