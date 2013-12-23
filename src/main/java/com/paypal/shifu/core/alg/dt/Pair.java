package com.paypal.shifu.core.alg.dt;

public class Pair {

	private long pos;
	private long neg;
	
	public Pair(){
		this.pos = 0;
		this.neg = 0;
	}
	
	public void increasePos(){
		if(pos < Long.MAX_VALUE) this.pos ++;
	}
	
	public void increaseNeg(){
		if(neg < Long.MAX_VALUE) this.neg ++;
	}
	
	public double getEntropy(){
		if(neg == 0 || pos == 0) return 0.;
		
		double total = getTotal();
		double posE = (double) (pos / total)  * Math.log(pos / total);
		double negE = (double) (neg / total)  * Math.log(neg / total);
		
		return -(posE + negE) / Math.log(2);
	}
	
	public double getTotal(){
		return (double)(pos + neg);
	}
}
