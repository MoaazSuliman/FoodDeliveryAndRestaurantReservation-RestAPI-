package com.moaaz.resturant.service;

import com.moaaz.resturant.model.Food;
import com.moaaz.resturant.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food addFood(Food food  ) {
        food.setTotal(getTotalPriceForFoodAfterDiscount(food));
        return foodRepository.save(food);
    }

    public Food updateFood(Food food) {
        checkIfFoodIsExistOrThrowException(food.getId());
        food.setTotal(getTotalPriceForFoodAfterDiscount(food));
        return foodRepository.save(food);
    }


    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public void deleteFoodById(int id) {
        checkIfFoodIsExistOrThrowException(id);
        foodRepository.deleteById(id);
    }

    public Food getFoodById(int foodId) {
        return foodRepository.findById(foodId).orElseThrow(
                () -> new NoSuchElementException("There Are No Food With Id = " + foodId));
    }

    public void checkIfFoodIsExistOrThrowException(int foodId) {
        getFoodById(foodId);
    }

    private double getTotalPriceForFoodAfterDiscount(Food food) {
        return food.getPrice() - (food.getPrice() * (food.getDiscount() / 100));

    }


}
