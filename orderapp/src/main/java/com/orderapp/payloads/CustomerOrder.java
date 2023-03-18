package com.orderapp.payloads;

import java.time.LocalDate;

public class CustomerOrder {
	
    private Long customerId;
    private String email;
    private String firstname;
    private String surname;
    private Long orderid;
    private Double amount;
    private LocalDate order_date;
    
	public CustomerOrder() {
		super();
	}

	public CustomerOrder(Long customerId, String email, String firstname, String surname, Long orderid, Double amount,
			LocalDate order_date) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
		this.orderid = orderid;
		this.amount = amount;
		this.order_date = order_date;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}
    
	
    
}
