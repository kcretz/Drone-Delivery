# Drone-Delivery
Pre-Requisite:
1. Install JDK jdk-17.0.12_windows-x64_bin
2. eclipse-jee-2024-12-R-win32-x86_64
3. Install Postman for REST API testing.
Steps to Build And Run:
1. Import Project As Maven>Existing Maven Project
2. Browse for the root folder of project and Apply
3. To Run the Project go to src/main/java under package com.hitachi.medication.delivery.app and find class     MedicationDeliveryAppApplication.java
4. Right-click.Run As Java Application 
5. Wait until "Application is Running!" displayed in console.
Steps to test:
1. Go to Postman
2. Import Collections in project with file name "DroneServiceDemo.postman_collection.json"
3. Test using REST API exposure services
For sample database:
1. Go to http://localhost:8080/h2-console/
2. Click Connect
