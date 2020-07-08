package com.allianz.coreader.service;

import com.allianz.coreader.models.Sensor;
import com.allianz.coreader.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorService {

    @Autowired
    private SensorRepository repository;

    public Boolean exists(String description) {
        Sensor sensor = repository.findByDescription(description);
        return sensor != null ;
    }

    public Boolean addSensor(Sensor sensor) {
        Sensor newSensor = repository.save(sensor);
        return newSensor.getId() != null;
    }
}
