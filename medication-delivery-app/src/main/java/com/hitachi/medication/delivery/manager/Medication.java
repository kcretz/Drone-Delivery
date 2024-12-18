package com.hitachi.medication.delivery.manager;

import java.net.URL;

import jakarta.validation.constraints.Pattern;

public class Medication {
	
	@Pattern(regexp="^[a-zA-Z0-9\\-_]+$", message="Field must only contain letters, numbers, hyphens and underscores")
	private String name;
	
	
	private Integer weight;
	
	@Pattern(regexp="^[A-Z0-9\\_]+$", message="Field must only contain uppercase letters, underscores and numbers")
	private String code;
	
	private URL image;
	
	public Medication() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public URL getImage() {
		return image;
	}

	public void setImage(URL image) {
		this.image = image;
	}


}
