package com.allianz.coreader.repositories;

import com.allianz.coreader.models.Sensor;
import org.springframework.data.repository.CrudRepository;


public interface SensorRepository extends CrudRepository<Sensor, String> {

    Sensor findByDescription(String description);

}