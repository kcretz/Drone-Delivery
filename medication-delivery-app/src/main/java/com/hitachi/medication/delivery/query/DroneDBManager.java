package com.hitachi.medication.delivery.query;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hitachi.medication.delivery.manager.Drone;
import com.hitachi.medication.delivery.manager.Medication;
import com.hitachi.medication.delivery.manager.Model;
import com.hitachi.medication.delivery.manager.State;

public class DroneDBManager implements IConnect {

	private Statement statement;
	private Connection connection;

	public DroneDBManager() {

	}

	@Override
	public void connect() {
		String jdbcurl = "jdbc:h2:mem:deliveryDB";
		String username = "sa";
		String password = "";
		try {

			this.connection = DriverManager.getConnection(jdbcurl, username, password);
			System.out.println("Connected to Drone Data Base Succesfully!");

			this.statement = connection.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void register(Drone drone) {
		String delimiter = ",";
		String fields = EnumDroneFields.serialNumber.name() + delimiter + EnumDroneFields.model.name() + delimiter
				+ EnumDroneFields.maxWeightLimit.name() + delimiter + EnumDroneFields.batteryCapacity.name() + delimiter
				+ EnumDroneFields.state.name();
		String sqlInsert = "INSERT INTO TB_DRONE(" + fields + ") VALUES (" + "\'" + drone.getSerialNumber() + "\'"
				+ delimiter + "\'" + drone.getModel() + "\'" + delimiter + "\'" + drone.getMaxWeightLimit() + "\'"
				+ delimiter + drone.getBatteryCapacity() + delimiter + "\'" + drone.getState() + "\'" + ")";
		try {
			this.statement.execute(sqlInsert);
			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Drone getDetails(String serialNumber, String field) {

		String paramSerialNumber = "\'" + serialNumber + "\'";
		String sqlSelect = "SELECT " + field + " FROM TB_DRONE WHERE " + EnumDroneFields.serialNumber.name() + "="
				+ paramSerialNumber;
		Drone droneSelect = new Drone();
		try {
			ResultSet result = this.statement.executeQuery(sqlSelect);

			droneSelect.setSerialNumber(serialNumber);
			while (result.next()) {
				droneSelect.setBatteryCapacity(result.getInt(field));
			}

			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return droneSelect;
	}

	public List<Drone> getAllAvailableDrone() {
		List<Drone> listOfDrone = new ArrayList<Drone>();
		String paramState = "\'" + State.IDLE.name() + "\'";
		String sqlSelect = "SELECT * FROM TB_DRONE WHERE " + EnumDroneFields.state.name() + "=" + paramState;

		try {
			ResultSet result = this.statement.executeQuery(sqlSelect);

			while (result.next()) {
				Drone drone = new Drone();
				if (State.IDLE.name().equalsIgnoreCase(result.getString(EnumDroneFields.state.name()))) {
					drone.setSerialNumber(result.getString(EnumDroneFields.serialNumber.name()));
					Model model = Model.valueOf(result.getString(EnumDroneFields.model.name()));
					drone.setModel(model);
					drone.setBatteryCapacity(result.getInt(EnumDroneFields.batteryCapacity.name()));
					drone.setState(State.IDLE);

					listOfDrone.add(drone);
				}
			}

			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfDrone;
	}

	public void load(Medication medication) {
		String delimiter = ",";
		String fields = EnumMedicationFields.droneId + delimiter + EnumMedicationFields.name.name() + delimiter
				+ EnumMedicationFields.weight.name() + delimiter + EnumMedicationFields.code.name() + delimiter
				+ EnumMedicationFields.image.name();
		String droneId = "\'" + medication.getDroneId() + "\'" + delimiter;
		String name = "\'" + medication.getName() + "\'" + delimiter;
		String weight = "\'" + medication.getWeight() + "\'" + delimiter;
		String code = "\'" + medication.getCode() + "\'" + delimiter;
		String image = "\'" + medication.getImage() + "\'";

		String sqlInsert = "INSERT INTO TB_MEDICATION(" + fields + ") VALUES (" + droneId + name + weight + code + image
				+ ")";
		try {
			this.statement.execute(sqlInsert);
			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Medication getMedicationOnDrone(String serialNumber) throws MalformedURLException {
		Medication medication = new Medication();
		String paramSerialNumber = "\'" + serialNumber + "\'";
		String sqlSelect = "SELECT * FROM TB_MEDICATION WHERE " + EnumMedicationFields.droneId.name() + "="
				+ paramSerialNumber;
		
		try {
			ResultSet result = this.statement.executeQuery(sqlSelect);
			while (result.next()) {
				medication.setDroneId(result.getString(EnumMedicationFields.droneId.name()));
				medication.setName(result.getString(EnumMedicationFields.name.name()));
				medication.setWeight(result.getInt(EnumMedicationFields.weight.name()));
				medication.setCode(result.getString(EnumMedicationFields.code.name()));
				medication.setImage(new URL(result.getString(EnumMedicationFields.image.name())));
				
			}
			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medication;
	}

}
