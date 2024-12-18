INSERT INTO TB_DRONE(serialNumber, model, maxWeightLimit, batteryCapacity, state) VALUES
('H123456', 'LightWeight', 250, 40, 'IDLE'),
('H123457', 'MiddleWeight', 500, 50, 'IDLE'),
('H123458', 'CruiseWeight', 750, 75, 'IDLE'),
('H123459', 'HeavyWeight', 1000, 100, 'IDLE');

INSERT INTO TB_MEDICATION(droneId, name, weight, code, image) VALUES
('H123456','Anti Hestamine', '200', 'AH001', 'https://url1'),
('H123457','Anti Persperant', '400','AP001', 'https://url2'),
('H123458','Anti Persperant', '700','AP002', 'https://url3'),
('H123459','Paracetamol', '1000','PA003', 'https://url4');

