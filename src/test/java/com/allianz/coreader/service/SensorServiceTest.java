package com.allianz.coreader.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.allianz.coreader.models.Sensor;
import com.allianz.coreader.repositories.SensorRepository;

@SpringBootTest
public class SensorServiceTest {

	@InjectMocks
	private SensorService sensorService;

	@Mock
	private SensorRepository repository;

	@Test
	public void isSensorServiceInjectedTest() {
		Assert.assertNotNull(sensorService);
	}

	private final String anExistentedDescription = "valid description";
	private final String anInexistentedDescription = "invalid description";

	@Test
	public void existsWithAnExistentedDescriptionTest() {

		Sensor sensor = new Sensor();
		sensor.setId("1");
		sensor.setDescription(anExistentedDescription);
		when(repository.findByDescription(anExistentedDescription)).thenReturn(sensor);
		assertTrue(sensorService.exists(anExistentedDescription));
		verify(repository, atLeastOnce()).findByDescription(anExistentedDescription);
	}

	@Test
	public void existsWithAnInexistentedDescriptionTest() {
		when(repository.findByDescription(anInexistentedDescription)).thenReturn(null);
		assertFalse(sensorService.exists(anInexistentedDescription));
		verify(repository, atLeastOnce()).findByDescription(anInexistentedDescription);
	}

	@Test
	public void existsWithNullDescriptionTest() {
		when(repository.findByDescription(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			sensorService.exists(null);
		});
		verify(repository, atLeastOnce()).findByDescription(null);
	}

	@Test
	public void addSensorWithAValidEntityTest() {

		Sensor newSensor = new Sensor();
		newSensor.setDescription(anExistentedDescription);
		newSensor.setDistrictId("1");

		Sensor savedSensor = new Sensor();
		savedSensor.setId("1");
		savedSensor.setDescription(anExistentedDescription);
		savedSensor.setDistrictId("1");

		when(repository.save(newSensor)).thenReturn(savedSensor);
		assertTrue(sensorService.addSensor(newSensor));
		verify(repository, atLeastOnce()).save(newSensor);
	}

	@Test
	public void addSensorWithAnInValidEntityTest() {

		Sensor newSensor = new Sensor();
		newSensor.setDescription(null);
		newSensor.setDistrictId("-1");

		when(repository.save(newSensor)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			sensorService.addSensor(newSensor);
		});
		verify(repository, atLeastOnce()).save(newSensor);
	}

	@Test
	public void addSensorWithNullEntityTest() {
		when(repository.save(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			sensorService.addSensor(null);
		});
		verify(repository, atLeastOnce()).save(any());
	}
}
