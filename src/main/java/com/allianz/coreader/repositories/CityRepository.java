package com.allianz.coreader.repositories;

import com.allianz.coreader.models.COReading;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<COReading, String> {

}
