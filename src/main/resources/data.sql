DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS sensor;
DROP TABLE IF EXISTS district;
DROP TABLE IF EXISTS coreading;

CREATE TABLE IF NOT EXISTS city (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS sensor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS district (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  id_city INT NOT NULL,
  id_sensor INT NOT NULL,
  FOREIGN KEY (id_city) REFERENCES public.city(id),
  FOREIGN KEY (id_sensor) REFERENCES public.sensor(id)
);

CREATE TABLE IF NOT EXISTS coreading (
  id INT AUTO_INCREMENT PRIMARY KEY,
  read_value REAL NOT NULL,
  id_district INT NOT NULL,
  read_time TIMESTAMP AS CURRENT_TIMESTAMP,
  FOREIGN KEY (id_district) REFERENCES public.district(id)
);

INSERT INTO city VALUES (1, 'Barcelona');

INSERT INTO sensor VALUES (1, 'Hospitalet Sensor');
INSERT INTO sensor VALUES (2, 'Lleida Sensor');
INSERT INTO sensor VALUES (3, 'Barcelona Sensor');

INSERT INTO district VALUES (1, 'Hospitalet de Llobregat', 1, 1);
INSERT INTO district VALUES (2, 'Lleida'                   , 1, 2);
INSERT INTO district VALUES (3, 'Barcelona Centro'         , 1, 3);