package com.allianz.coreader.managers;

import com.allianz.coreader.models.Sensor;
import com.allianz.coreader.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorManager {

    @Autowired
    private SensorRepository repository;

    public Boolean exists(String description) {
        Sensor sensor = repository.findByDescription(description);
        return sensor != null ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean addSensor(Sensor sensor) {
        Sensor newSensor = repository.save(sensor);
        return newSensor.getId() != null ? Boolean.TRUE : Boolean.FALSE;
    }
}
