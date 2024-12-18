package com.hitachi.medication.delivery.manager;


import java.util.ArrayList;
import java.util.List;

import com.hitachi.medication.delivery.query.DroneDBManager;

public class DeliveryManager implements IDeliver {

	public List<Drone> drones = new ArrayList<Drone>();
	private static final Integer MINIMUM_CAPACITY = 25;
	
	private Drone drone;
	private Medication medication;

	public DeliveryManager(Drone drone, Medication medication) {
		this.drone = drone;
		this.medication = medication;
	}

	@Override
	public void update() {

		for (Drone eachDrone : drones) {
			if(eachDrone.getModel() == drone.getModel()) {
				eachDrone.setState(drone.getState());
			}
		}
		
	}

	@Override
	public void deliver() {
		for (Drone eachDrone : this.drones) {
			if(eachDrone.getModel() == drone.getModel()) {
				if(eachDrone.getState() == State.LOADED) {
					drone.setState(State.DELIVERING);
				}
			}
		}
		
	}

	@Override
	public void load() {
		for (Drone eachDrone : this.drones) {
			
			if(eachDrone.getModel() == drone.getModel()) {
				
				if(eachDrone.getState() == State.IDLE) {
					continue;
				}			
				
				if(eachDrone.getBatteryCapacity() >= MINIMUM_CAPACITY) {
					eachDrone.setState(State.LOADING);
				}	
				
				if(eachDrone.getState() == State.LOADING) {
					
					if (eachDrone.getMaxWeightLimit() < medication.getWeight()) {
						eachDrone.setState(State.LOADED);
					}
				}
			}
			
		}
		
	}


}
