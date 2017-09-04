package algorithms;

import main.RandomPatternGenerator;

/* Bases the score on the expected value of the remaining number of
 * codes after the guess.
 */

public class Average extends SListAI {

	public Average(int patternLength, int colors) {
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
		
		// find negative expected value of remaining
		result[0] = 0;
		for(int i : partition) {
			result[0] -= (i*i)/(double)sum;
		}
		
		return result;
	}

}
