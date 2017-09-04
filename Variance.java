package algorithms;

import main.RandomPatternGenerator;

/* Bases the score of a potential guess based on the variance of the
 * remaining codes.
 */

public class Variance extends SListAI {

	public Variance(int patternLength, int colors) {
		super(patternLength, colors);
	}

	@Override
	public int[] makeCode() {
		RandomPatternGenerator r;
		r = new RandomPatternGenerator(patternLength, colors);
		return r.makeCode();
	}

	@Override
	public double[] getScores(int[] partition) {
		double[] result = new double[1];
		
		// sum the partition
		int sum = sumUnsignedIntArray(partition);
		
		// find expected value of remaining
		double mu = 0;
		for(int i : partition) {
			mu += (i*i)/(double)sum;
		}
		
		// find multiple of negative variance of the partition
		result[0] = 0;
		for(int i : partition) {
			result[0] -= i*(i - mu)*(i - mu);
		}
		
		return result;
	}

}
