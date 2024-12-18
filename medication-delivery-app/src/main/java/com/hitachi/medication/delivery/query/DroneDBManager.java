package com.hitachi.medication.delivery.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hitachi.medication.delivery.manager.Drone;

public class DroneDBManager implements IConnect {

	private Drone drone;
	private Statement statement;
	private Connection connection;

	public DroneDBManager(Drone drone) {
		this.drone = drone;
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

	public void register() {
		String delimiter = ",";
		String fields = DroneFields.serialNumber.name() + delimiter + DroneFields.model.name() + delimiter
				+ DroneFields.maxWeightLimit.name() + delimiter + DroneFields.batteryCapacity.name() + delimiter
				+ DroneFields.state.name();
		String sqlInsert = "INSERT INTO TB_DRONE(" + fields + ") VALUES (" + "\'" + this.drone.getSerialNumber() + "\'"
				+ delimiter + "\'" + this.drone.getModel() + "\'" + delimiter + "\'" + this.drone.getMaxWeightLimit()
				+ "\'" + delimiter + this.drone.getBatteryCapacity() + delimiter + "\'" + this.drone.getState() + "\'"
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

	public Drone getDetails(String serialNumber, String field) {

		String paramSerialNumber = "\'" + serialNumber + "\'";
		String sqlSelect = "SELECT " + field + " FROM TB_DRONE WHERE " + DroneFields.serialNumber.name() + "="
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

}
