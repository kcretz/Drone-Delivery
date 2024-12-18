package com.hitachi.medication.delivery.app;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitachi.medication.delivery.manager.Drone;
import com.hitachi.medication.delivery.query.DroneDBManager;
import com.hitachi.medication.delivery.query.DroneFields;

@RestController
@RequestMapping("/droneservice")
public class DroneControllerAPI {

	private Drone drone;

	@GetMapping("/get/{serialNumber}")
	public Drone getBatteryCapacity(@PathVariable String serialNumber) {

		DroneDBManager dbMgr = new DroneDBManager(drone);
		dbMgr.connect();

		return dbMgr.getDetails(serialNumber, DroneFields.batteryCapacity.name());

	}

	@PostMapping("/create")
	public String createDroneDetails(@RequestBody Drone drone) {

		DroneDBManager dbMgr = new DroneDBManager(drone);
		dbMgr.connect();
		dbMgr.register();
		return "Added Drone Details succesfully!";

	}

	@PutMapping("/update")
	public String updateDroneDetails(@RequestBody Drone drone) {
		this.drone = drone;
		return "Updated Drone Details succesfully!";

	}

	@DeleteMapping("/delete")
	public String deleteDroneDetails(String serialNumber) {
		this.drone = null;
		return "Deleted Drone Details succesfully!";

	}

}
