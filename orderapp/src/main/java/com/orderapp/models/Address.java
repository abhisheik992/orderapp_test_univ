package com.orderapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "address")
	private String address;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "region")
	private String region;

	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private Customer customer;

	public Address() {
		super();
	}

	public Address(Long id, String address, String zipCode, String region, Customer customer) {
		super();
		this.id = id;
		this.address = address;
		this.zipCode = zipCode;
		this.region = region;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", zipCode=" + zipCode + ", region=" + region
				+ ", customer=" + customer + "]";
	}
	
}
