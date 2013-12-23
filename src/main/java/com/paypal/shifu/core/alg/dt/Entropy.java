package com.paypal.shifu.core.alg.dt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import bsh.StringUtil;

public class Entropy {
	
	static public double doEntropy(List<Record> dataset, String colName, boolean isTarget) {
		
		if(isTarget || colName == null) {
			colName = dataset.get(0).getTargetColName();
		}

		Map<String, Pair> colSets = new HashMap<String, Pair>();

		for (int i = 0; i < dataset.size(); i++) {
			Record record = dataset.get(i);

			String target = record.getTarget();
			String div = "target";
			
			if(!isTarget)  div = record.getAttribute(colName);

			Pair pair = null;
			if (colSets.containsKey(div)) {
				pair = colSets.get(div);
			} else {
				pair = new Pair();
			}

			// after normalize, the pos is 1, neg is 0;
			if (target.equals("1")) {
				pair.increasePos();
			} else if (target.equals("0")) {
				pair.increaseNeg();
			}

			colSets.put(div, pair);
		}
		
		double entropy = 0.0;

		Iterator<Entry<String, Pair>> iter = colSets.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Pair> entry = iter.next();
			Pair pair = (Pair) entry.getValue();
			double total = pair.getTotal();
			entropy += total  * pair.getEntropy() / dataset.size();
		}
		return entropy;
	}
}
