package com.muddycottage.muddyserver.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.muddycottage.muddyserver.model.DataItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.muddycottage.muddyserver.utils.JsonDataDeserialiser;

public class DataFeedDto {
	private final Logger logger = LoggerFactory.getLogger(DataFeedDto.class) ;
	
	@JsonDeserialize(using = JsonDataDeserialiser.class)
	private Date captureDateTime ;
	
	private String location ;
	
	private String ping ;

	private List<DataItemDto> dataList ;
	
	public DataFeedDto () {}

	public DataFeedDto(Date captureDateTime, String location, List<DataItemDto> dataList) {
		super();
		this.captureDateTime = captureDateTime;
		this.location = location;
		this.dataList = dataList ;
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
	
	public String getPing() {
		return ping;
	}

	public void setPing(String ping) {
		this.ping = ping;
	}

	public boolean isPing() { return ! "*".equals(ping) ; }
	
	public String getPingInfo () {
		return String.format("%s : %s", location, captureDateTime) ;
	}
	
	public List<DataItemDto> getDataList() {
		return dataList;
	}

	public void setDataList(List<DataItemDto> dataList) {
		this.dataList = dataList;
	}

	@JsonIgnore
	public List<DataItem> toModel () {
		if ( (dataList == null) || (dataList.size() == 0) )
			return null ;
		
		List<DataItem> modelList = null ;
		
		for (DataItemDto dataItemDto : dataList) {
			if (modelList == null)
				modelList = new ArrayList<DataItem> () ;
			
			DataItem dataItem = new DataItem(this, dataItemDto) ;
			
			modelList.add(dataItem) ;
		}
		
		return modelList ;
	}
}
