package com.muddycottage.muddyserver.dto;

public class DataItemDto {
	private String name ;
	private String type ;
	private float value ;
	
	public DataItemDto() {}
	
	public DataItemDto(String name, String type, float value) {
		super();
		this.name = name;
		this.type = type ;
		this.value = value;
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
}
