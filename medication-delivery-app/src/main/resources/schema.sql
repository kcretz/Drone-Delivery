CREATE TABLE TB_DRONE(
id INT AUTO_INCREMENT PRIMARY KEY,
serialNumber VARCHAR(100) NOT NULL,
model VARCHAR(100) NOT NULL,
maxWeightLimit INT NOT NULL,
batteryCapacity INT NOT NULL,
state VARCHAR(100) NOT NULL
);

CREATE TABLE TB_MEDICATION(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
weight INT NOT NULL,
code VARCHAR(100) NOT NULL,
image VARCHAR(250) NOT NULL
);
