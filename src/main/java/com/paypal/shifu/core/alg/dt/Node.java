package com.paypal.shifu.core.alg.dt;

import java.util.*;

public class Node {
	
	private Node parent;
	private String colName;
	private int index;
	private Node[] children;
	
	private List<Pair> counts;
	
	private boolean isLeaf;
	
	public Node(String name, int index){
		this.isLeaf = false;
		this.colName   = name;
		this.index  = index;
	}
	
	/**
	 * the value size should be equal to target size
	 * @param value for this column, 
	 * @param target 0,1 pair
	 */
	public void doCalculate(String[] values, int[] targets) {
		Map<String, Pair> categories = new HashMap<String, Pair>();
		
		long neg = 0, pos = 0;
		
		for(int i = 0 ; i < values.length; i ++) {
			String value = values[i];
			int target   = targets[i];
			Pair pair    = null;
			if(categories.containsKey(value)) {
				pair = categories.get(value);
			} else {
				pair = new Pair(value);
			}
			if(target == 1){
				pair.increasePos();
				pos ++;
			} else if(target == 0) {
				pair.increaseNeg();
				neg ++;
			}
			categories.put(value, pair);
		}
		
		
	}
	
	public class Pair {
		
		private String div;
		private long pos;
		private long neg;
		
		public Pair(String name){
			this.pos = 0;
			this.neg = 0;
			this.div = name;
		}
		
		public void increasePos(){
			if(pos < Long.MAX_VALUE) this.pos ++;
		}
		
		public void increaseNeg(){
			if(neg < Long.MAX_VALUE) this.neg ++;
		}

		private Node getOuterType() {
			return Node.this;
		}
		
		public double getEntropy(){
			if(neg == 0 || pos == 0) return 0.;
			
			double total = getTotal();
			double posE = (double) (pos / total)  * Math.log(pos / total);
			double negE = (double) (neg / total)  * Math.log(neg / total);
			
			return (posE + negE) / Math.log(2);
		}
		
		public double getTotal(){
			return (double)(pos + neg);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((div == null) ? 0 : div.hashCode());
			result = prime * result + (int) (neg ^ (neg >>> 32));
			result = prime * result + (int) (pos ^ (pos >>> 32));
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (div == null) {
				if (other.div != null)
					return false;
			} else if (!div.equals(other.div))
				return false;
			if (neg != other.neg)
				return false;
			if (pos != other.pos)
				return false;
			return true;
		}

	}
}
