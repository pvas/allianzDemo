DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS sensor;
DROP TABLE IF EXISTS district;
DROP TABLE IF EXISTS coreading;

CREATE TABLE IF NOT EXISTS city (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS district (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  id_city INT NOT NULL,
  FOREIGN KEY (id_city) REFERENCES public.city(id)
);

CREATE TABLE IF NOT EXISTS sensor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  id_district INT NOT NULL,
  FOREIGN KEY (id_district) REFERENCES public.district(id)
);

CREATE TABLE IF NOT EXISTS coreading (
  id INT AUTO_INCREMENT PRIMARY KEY,
  read_value DECIMAL NOT NULL,
  id_sensor INT NOT NULL,
  read_time TIMESTAMP AS CURRENT_TIMESTAMP,
  FOREIGN KEY (id_sensor) REFERENCES public.sensor(id)
);

INSERT INTO city VALUES (1, 'Barcelona');

INSERT INTO district VALUES (1, 'Hospitalet de Llobregat', 1);
INSERT INTO district VALUES (2, 'Lleida'                 , 1);
INSERT INTO district VALUES (3, 'Barcelona Centro'       , 1);

INSERT INTO sensor VALUES (1, 'Hospitalet Sensor', 1);
INSERT INTO sensor VALUES (2, 'Lleida Sensor',     2);
INSERT INTO sensor VALUES (3, 'Barcelona Sensor',  3);

