package com.moaaz.resturant.service;

import com.moaaz.resturant.model.Category;
import com.moaaz.resturant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);

    }

    public Category updateCategory(Category category) {
        checkIfCategoryIsExistOrThrowException(category.getId());
        return categoryRepository.save(category);
    }


    public void deleteCategoryById(int id) {
        checkIfCategoryIsExistOrThrowException(id);
        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int categoryId) {

        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new NoSuchElementException("There Are No Category With categoryId  = " + categoryId)
        );
    }

    public void checkIfCategoryIsExistOrThrowException(int categoryId) {
        getCategoryById(categoryId);
    }
}
