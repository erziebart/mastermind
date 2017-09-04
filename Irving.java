package algorithms;

import main.RandomPatternGenerator;

/* Algorithm bases score on expected value of the square of the
 * codes remaining for a given guess - developed by Rob Irving in
 * 1979.
 */
public class Irving extends SListAI {

	public Irving(int patternLength, int colors) {
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
		
		// find negative expected value of remaining^2
		result[0] = 0;
		for(int i : partition) {
			result[0] -= (i*i*i)/(double)sum;
		}
		
		return result;
	}

}
