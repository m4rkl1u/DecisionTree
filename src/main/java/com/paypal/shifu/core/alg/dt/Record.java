package com.paypal.shifu.core.alg.dt;

import java.util.Map;

public class Record{
	
	private Map<String, String> value;
	private String target;
	
	public Record(Map<String, String> value) {
		super();
		this.value = value;
	}
	
	public void setTargetColName(String name){
		target = name;
	}
	
	public String getTargetColName() {
		return target;
	}
	
	public String getTarget(){
		return value.get(target);
	}

	/**
	 * @return the value
	 */
	public Map<String, String> getAttribute() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setAttribute(Map<String, String> value) {
		this.value = value;
	}
	
	public String getAttribute(String name){
		return value.get(name);
	}
}
