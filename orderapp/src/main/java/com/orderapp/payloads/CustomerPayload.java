package com.orderapp.payloads;

import java.util.List;

import com.orderapp.models.Address;

public class CustomerPayload {

	private Long id;
	private String firstName;
	private String surName;
	private String fullName;
	private String email;
	private boolean status;
	private Address address;
	private List<OrderPayload> ordersList;

	public CustomerPayload() {
		super();
	}

	public CustomerPayload(Long id, String firstName, String surName, String fullName, String email, boolean status,
			Address address, List<OrderPayload> ordersList) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
		this.fullName = fullName;
		this.email = email;
		this.status = status;
		this.address = address;
		this.ordersList = ordersList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<OrderPayload> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<OrderPayload> ordersList) {
		this.ordersList = ordersList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


}
