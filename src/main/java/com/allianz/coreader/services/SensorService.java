package com.allianz.coreader.services;

import com.allianz.coreader.models.Sensor;
import com.allianz.coreader.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public Boolean exists(String description) {
        Sensor sensor = sensorRepository.findByDescription(description);
        return sensor != null ;
    }

    public Boolean addSensor(Sensor sensor) {
        Sensor newSensor = sensorRepository.save(sensor);
        return newSensor.getId() != null;
    }
}
