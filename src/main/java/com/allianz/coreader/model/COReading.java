package com.allianz.coreader.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class COReading {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "read_value")
	private double coTwoMeasure;
	
	@Column(name = "read_value")
	private Time timeStamp;
	
	
	 /* id INT AUTO_INCREMENT PRIMARY KEY,
	  read_value REAL NOT NULL,
	  id_district INT NOT NULL,
	  read_time TIMESTAMP AS CURRENT_TIMESTAMP,
	  FOREIGN KEY (id_district) REFERENCES public.district(id)*/

}
