package com.paypal.shifu.core.alg.dt;

public class Record{
	private String[] value;
	private boolean isInverse = false;
	/**
	 * @return the isInverse
	 */
	public boolean isInverse() {
		return isInverse;
	}
	/**
	 * @param isInverse the isInverse to set
	 */
	public void setInverse(boolean isInverse) {
		this.isInverse = isInverse;
	}
	/**
	 * @return the value
	 */
	public String[] getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String[] value) {
		this.value = value;
	}
}
