package algorithms;

import main.RandomPatternGenerator;

/* Score of a partition is the maximum possible number of remaining
 * codes after the guess is made - algorithm invented by Donald Knuth
 * in 1977.
 */
public class Knuth extends SListAI {

	public Knuth(int patternLength, int colors) {
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
		result[0] = -maxUnsignedIntArray(partition);
		return result;
	}
}
