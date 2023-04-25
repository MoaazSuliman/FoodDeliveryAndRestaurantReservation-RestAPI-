package com.moaaz.resturant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "person_user")
public class User extends Person {


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    public User() {
    	
    }

    public User(List<Order> orders, Cart cart) {
		super();
		this.orders = orders;
		this.cart = cart;
	}

	public void addFoodToCart(Food food) {
        cart.addFood(food);
    }

    public void addOrder(Order order) {
        if (this.orders == null)
            this.orders = new ArrayList<>();
        orders.add(order);
    }

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
    
    
    
}
