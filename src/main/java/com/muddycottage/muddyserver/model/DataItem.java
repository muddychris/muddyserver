package com.muddycottage.muddyserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.muddycottage.muddyserver.dto.DataFeedDto;
import com.muddycottage.muddyserver.dto.DataItemDto;

//@SequenceGenerator(name = "MY_GENERATOR", sequenceName = "seq_data_item")
@Table(name = "data_logger")
@Entity
public class DataItem {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id ;
	
	@Column(name = "capture_date_time")
	private Date captureDateTime ;
	private String location ;
	
	@Column(name = "data_name")
	private String name ;
	@Column(name = "data_type")
	private String type ;
	@Column(name = "data_value")
	private float value ;
	
	public DataItem () {} 
	
	public DataItem (DataFeedDto dataFeedDto, DataItemDto  dataItemDto) {
		this.captureDateTime = dataFeedDto.getCaptureDateTime() ;
		this.location = dataFeedDto.getLocation() ;
		
		this.name = dataItemDto.getName() ;
		this.type = dataItemDto.getType() ;
		this.value = dataItemDto.getValue() ;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCaptureDateTime() {
		return captureDateTime;
	}
	
	public void setCaptureDateTime(Date captureDateTime) {
		this.captureDateTime = captureDateTime;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public String toString () {
		return String.format("%s %s : %s %s %s", captureDateTime, location, name, type, value) ;
	}
}
