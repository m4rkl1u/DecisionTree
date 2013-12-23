package com.paypal.shifu.core.alg.dt;

import java.util.*;

public class Node {
	
	//key value define the path
	private String name;
	private String category;
	
	private Node parent;
	private List<Node> children;

	private List<Record> data;
	
	private Set<String> categories;
	
	//total entropy
	private double entropy;
	
	private boolean isUsed;
	
	public Node() {
		data = new ArrayList<Record>();
		categories = new HashSet<String>();
		children = new ArrayList<Node>();
		setEntropy(0.0);
	}
	
	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 * @return the data
	 */
	public List<Record> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Record> data) {
		this.data = data;
	}

	/**
	 * @return the entropy
	 */
	public double getEntropy() {
		return entropy;
	}

	/**
	 * @param entropy the entropy to set
	 */
	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}

	/**
	 * @return the isUsed
	 */
	public boolean isUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int getChildrenSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public Set<String> doDivide() {
		
		if(categories.size() != 0) {
			return this.categories;
		}
		
		for(int i = 0 ; i < this.data.size(); i ++) {
			Record r = data.get(i);
			this.categories.add(r.getAttribute(this.name));
		}
		
		return this.categories;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}
}
