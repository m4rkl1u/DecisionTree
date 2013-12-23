package com.paypal.shifu.core.alg.dt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Decision Tree implementation, breath first, categorical only.
 * 
 * @author mark
 *
 */
public class DecisionTree {

	private boolean[] attributeUsed;
	private String[] attribute;

	public DecisionTree(final String[] attribute, int target) {
		this.attributeUsed = new boolean[attribute.length]; // default false
		this.attribute = Arrays.copyOf(attribute, attribute.length);
		this.attributeUsed[target] = true;
	}

	public Node buildTree(Node node, List<Record> dataset) {

		node.setEntropy(Entropy.doEntropy(node.getData(), null, true));

		// we already get the closed
		if (node.getEntropy() - 0. < 1e-3) {
			return node;
		}

		int bestSelect = -1;
		double bestGain = 0;

		for (int i = 0; i < attributeUsed.length; i++) {
			if (!attributeUsed[i]) {

				double loss = Entropy.doEntropy(dataset, attribute[i], false);

				// if we put i in this node, how much could we get?
				double gain = node.getEntropy() - loss;

				if (gain > bestGain) {
					bestGain = gain;
					bestSelect = i;
				}
			}
		}

		if (bestSelect != -1) {
			attributeUsed[bestSelect] = true;
			node.setName(attribute[bestSelect]);
			Set<String> categories = node.doDivide();

			for (Iterator<String> iter = categories.iterator(); iter.hasNext();) {
				String category = iter.next();

				List<Record> subset = subset(node.getData(),
						attribute[bestSelect], category);

				if (!isSeparated(subset)) {
					Node child = new Node();
					child.setParent(node);
					child.setData(subset);
					child.setCategory(category);
					node.addChild(child);

					buildTree(child, subset);
				}
			}

			// delete big data
			node.setData(null);
		}

		return node;
	}

	private boolean isSeparated(List<Record> dataset) {

		String name = dataset.get(0).getTargetColName();
		int posCount = 0, negCount = 0;

		for (int i = 0; i < dataset.size(); i++) {

			String value = dataset.get(i).getAttribute(name);

			if (value.equals("1")) {
				posCount++;
			} else if (value.equals("0")) {
				negCount++;
			}
		}

		return posCount == 0 || negCount == 0;
	}

	private List<Record> subset(List<Record> dataset, String attr,
			String category) {
		List<Record> subset = new ArrayList<Record>();

		for (int i = 0; i < dataset.size(); i++) {
			Record record = dataset.get(i);

			if (record.getAttribute().containsKey(attr)
					&& (category == null || record.getAttribute()
							.containsValue(category))) {
				subset.add(record);
			}
		}
		return subset;
	}

	public static void main(String[] args) throws IOException {

		List<Record> dataset = new ArrayList<Record>();

		String[] attributes = new String[] { "outlook", "temp", "humidity",
				"wind", "play" };

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"dt.input")));

		String line;
		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			String[] inputs = line.split(",");

			Map<String, String> value = new HashMap<String, String>();

			for (int i = 0; i < inputs.length; i++) {
				value.put(attributes[i], inputs[i]);
			}

			Record r = new Record(value);
			r.setTargetColName(attributes[attributes.length - 1]);

			dataset.add(r);
		}

		reader.close();

		DecisionTree tree = new DecisionTree(attributes, attributes.length - 1);

		Node root = new Node();
		root.setData(dataset);
		root.setParent(null);

		tree.buildTree(root, dataset);
	}
}
