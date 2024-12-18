package com.hitachi.medication.delivery.manager;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Drone {

	@NotBlank(message = "Serial must not be empty!")
	@Size(message = "Serial number must be 100 charater max")
	private String serialNumber;

	private Model model;

	@Max(value = 1000, message = "The maximum weight limit is 1000 grams")
	private Integer maxWeightLimit;

	@Max(value = 100, message = "Battery Capacity must not exceed 100%")
	private Integer batteryCapacity;

	@NotNull(message = "State must not be null")
	private State state;

	public Drone() {
		// TODO Auto-generated constructor stub
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Integer getMaxWeightLimit() {
		return maxWeightLimit;
	}

	public void setMaxWeightLimit(Integer maxWeightLimit) {
		this.maxWeightLimit = maxWeightLimit;
	}

	public Integer getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(Integer batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
