package com.moaaz.resturant.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_user")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    private List<Food> foods;
    @Column(name = "order_date")
    private String date;
    private boolean open;
    private double total;

    public Order() {

    }

    public Order(int id, List<Food> foods, String date, boolean open, double total) {
        super();
        this.id = id;
        this.foods = foods;
        this.date = date;
        this.open = open;
        this.total = total;
    }

    public void addFoodForOrder(Food food) {
        if (this.foods == null)
            this.foods = new ArrayList<>();
        this.foods.add(food);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", foods=" + foods +
                ", date='" + date + '\'' +
                ", open=" + open +
                ", total=" + total +
                '}';
    }
}
