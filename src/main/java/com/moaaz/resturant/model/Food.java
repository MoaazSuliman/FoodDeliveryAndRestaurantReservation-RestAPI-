package com.moaaz.resturant.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String detail;
	private String url;
	private double price;
	private double discount;
	private double total;

	@ManyToOne
	private Category category;

	public Food() {

	}

	public Food(int id, String name, String detail, String url, double price, double discount, double total,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.url = url;
		this.price = price;
		this.discount = discount;
		this.total = total;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
