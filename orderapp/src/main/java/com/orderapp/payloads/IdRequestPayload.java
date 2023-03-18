package com.orderapp.payloads;

public class IdRequestPayload {

	private Long id;
	private String category;

	public IdRequestPayload(Long id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
