package com.paypal.shifu.core.alg.dt;

public class DataSet {

	private int target = 0;
	private Record[] data;
	private Record[] invData;
	private String[] attribute;
	
	
	public Record[] inverse(){
		
		if(invData != null) return invData;
		
		return null;
	}
}
