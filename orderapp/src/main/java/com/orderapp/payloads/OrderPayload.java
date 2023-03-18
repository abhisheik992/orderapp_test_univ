package com.orderapp.payloads;

import java.time.LocalDate;

import com.orderapp.models.Customer;

public class OrderPayload {

	private Long id;
	private LocalDate orderDate;
	private Customer customer;
	private Double amount;
	
	public OrderPayload() {
		super();
	}

	public OrderPayload(Long id, LocalDate orderDate, Customer customer, Double amount) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.customer = customer;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
