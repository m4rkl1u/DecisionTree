package com.paypal.shifu.core.alg.dt;

public class Entropy {

	static public double entropy(double[] probabilities) {
		if (probabilities == null)
			throw new IllegalArgumentException("Invalid 'null' array");

		double sum = 0.;
		double result = 0.;

		for (int i = 0; i < probabilities.length; i++) {
			if (probabilities[i] < 0.)
				throw new IllegalArgumentException("Invalid negative "
						+ "probability");

			if (probabilities[i] > 0.) {
				result -= probabilities[i] * Math.log(probabilities[i]);
				sum += probabilities[i];
			}
		}

		if (sum <= 0.)
			return 0.;

		result += sum * Math.log(sum);

		return result / (Math.log(2.) * sum);
	}
}
