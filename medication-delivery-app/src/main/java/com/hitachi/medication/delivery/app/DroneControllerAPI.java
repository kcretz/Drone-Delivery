package com.hitachi.medication.delivery.app;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hitachi.medication.delivery.manager.Drone;
import com.hitachi.medication.delivery.manager.Medication;
import com.hitachi.medication.delivery.query.DroneDBManager;
import com.hitachi.medication.delivery.query.EnumDroneFields;

@RestController
@RequestMapping("/droneservice")
public class DroneControllerAPI {

	private Drone drone;

	@GetMapping("/checkdbattery/{serialNumber}")
	public Drone getBatteryCapacity(@PathVariable String serialNumber) {

		DroneDBManager dbMgr = new DroneDBManager();
		dbMgr.connect();
		return dbMgr.getDetails(serialNumber, EnumDroneFields.batteryCapacity.name());

	}
	
	@GetMapping("/checkdrones")
	public List<Drone> getDroneAvailability() {

		DroneDBManager dbMgr = new DroneDBManager();
		dbMgr.connect();
		return dbMgr.getAllAvailableDrone();

	}
	
	@GetMapping("/checkmeds/{serialNumber}")
	public Medication getMedicationOnDrone(@PathVariable String serialNumber) throws MalformedURLException {

		DroneDBManager dbMgr = new DroneDBManager();
		dbMgr.connect();
		return dbMgr.getMedicationOnDrone(serialNumber);

	}

	@PostMapping("/register")
	public String createDroneDetails(@RequestBody Drone drone) {

		DroneDBManager dbMgr = new DroneDBManager();
		dbMgr.connect();
		dbMgr.register(drone);
		return "Drone has been successfully registered!";

	}
	
	@PostMapping("/loadmeds")
	public String loadMedication(@RequestBody Medication medication) {

		DroneDBManager dbMgr = new DroneDBManager();
		dbMgr.connect();
		dbMgr.load(medication);
		return "Medication is loading!";

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
