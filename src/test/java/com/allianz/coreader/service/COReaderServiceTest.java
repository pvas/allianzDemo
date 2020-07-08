package com.allianz.coreader.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import com.allianz.coreader.models.COReading;
import com.allianz.coreader.models.Sensor;
import com.allianz.coreader.repositories.COReadingRepository;
import com.allianz.coreader.repositories.SensorRepository;

@SpringBootTest
public class COReaderServiceTest {

	@InjectMocks private COReaderService coReaderService;
	
	@Mock
	COReadingRepository coReadingRepository;

	@Mock
	SensorRepository sensorRepository;
	
	private COTwoMeasureDTO coTwoMeasureDTO;
	
	@BeforeEach
	public void initTestData() {
		this.coTwoMeasureDTO = new COTwoMeasureDTO();
	}
	
	
	@Test
	public void isCOReaderServiceInjectedTest() {
		Assert.assertNotNull(coReaderService);
	}
	
	@Test
	public void processCOValueWithValidDTOTest() {
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId("1");
	
		coReaderService.processCOValue(coTwoMeasureDTO);
		verify(coReadingRepository, atLeastOnce()).save(any(COReading.class));
	}
	
	@Test
	public void processCOValueWithNullDTOTest() {
		assertThrows(NullPointerException.class, () -> {
			coReaderService.processCOValue(null);
	    });
		verify(coReadingRepository, never()).save(any(COReading.class));
	}
	
	
	@Test
	public void processCOValueWithInvalidDTOTest() {
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId("-1");
	
		coReaderService.processCOValue(coTwoMeasureDTO);
		verify(coReadingRepository, atLeastOnce()).save(any(COReading.class));
	}
	
	@Test
	public void doesSensorExistWithAValidIdTest() {
		
		Sensor sensor = new Sensor();
		sensor.setDescription("This is an existented sensor");
		sensor.setId("1");
			
		Optional<Sensor> sensorOptional = Optional.of((Sensor) sensor);
		when(sensorRepository.findById("1")).thenReturn(sensorOptional);
		
		assertTrue(coReaderService.doesSensorExist("1"));
		verify(sensorRepository, atLeastOnce()).findById(any(String.class));
	}
	
	
	@Test
	public void doesSensorExistWithAInexistentedIdTest() {

		when(sensorRepository.findById("1")).thenReturn(Optional.empty());
		assertFalse(coReaderService.doesSensorExist("1"));
		verify(sensorRepository, atLeastOnce()).findById(any(String.class));
	}
	
	@Test
	public void doesSensorExistWithNullIdTest() {
		when(sensorRepository.findById(null)).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> {
			coReaderService.doesSensorExist(null);
	    });	
		verify(sensorRepository, atLeastOnce()).findById(any());
	}	
}
