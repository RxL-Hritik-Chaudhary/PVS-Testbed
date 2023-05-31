package com.rxlogix.pvSignalTest.dto;

public class NativeQueryDTO {
	private String id;
	private Integer version;
	private Integer defaultName;
	private String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getDefault_name() {
		return defaultName;
	}
	public void setDefault_name(Integer defaultName) {
		this.defaultName = defaultName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "NativeQueryDTO [id=" + id + ", version=" + version + ", defaultName=" + defaultName + ", name=" + name
				+ "]";
	}
	
	
	
	
}
