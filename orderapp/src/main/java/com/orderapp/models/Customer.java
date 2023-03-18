package com.orderapp.models;

import java.util.List;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "FirstName is a mandatory filed.")
	@Column(length = 60, name = "firstname")
	private String firstName;
	
	@NotBlank(message = "SurName is a mandatory filed.")
	@Column(length = 60, name = "surname")
	private String surName;
	
	@Column(name = "fullname")
	private String fullName;
	
	@NotBlank(message = "Email is a mandatory filed.")
	@Column(length = 60, name = "email")
	private String email;
	
	@Column(name = "status")
	private boolean status = true;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    
	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Order> ordersList;

	public Customer() {
		super();
	}

	public Customer(Long id, String firstName, String surName, String fullName, String email, boolean status,
			Address address, List<Order> ordersList) {
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

	public List<Order> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Order> ordersList) {
		this.ordersList = ordersList;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", surName=" + surName + ", email="
				+ email + ", status=" + status + ", address=" + address + "]";
	}

}
