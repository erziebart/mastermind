package algorithms;

import main.Indicator;
import main.Pattern;
import main.TestAI;

public abstract class SListAI extends TestAI {

	protected boolean[] S;
	protected int[] firstGuess;
	protected int checkFirst;
	
	public SListAI(int patternLength, int colors) {
		super(patternLength, colors);
		S = new boolean[(int)Math.pow(colors, patternLength)];
		init();
		firstGuess = new int[patternLength];
		checkFirst = 0;
	}
	
	public void init() {
		// set all codes to possible
		for(int i = 0; i < S.length; i++) {
			S[i] = true;
		}
	}
	
	public void setFirstGuess(int[] pattern) {
		firstGuess = pattern;
	}
	
	public void setCheckFirst(int hash) {
		checkFirst = hash;
	}
	
	public int getRemaining() {
		int count = 0;
		for(boolean b : S) {
			if(b) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints) {
		if(turn == 0) { // first guess
			if(firstGuess == null) {
				return makeCode();
			} else {
				return firstGuess;
			}
		} 
		
		// subsequent guesses
		else {
			// update the table
			update(guesses[turn-1], hints[turn-1]);
			
			// find best pattern using scoring algorithm
			return Pattern.unHash(getBest(), patternLength, colors);
		}
	}
	
	protected int getBest() {
		int best = checkFirst;
		double[] bestScores = null;
		
		int current; // current pattern
		double[] curScores = null;
		for(int i = 0; i < S.length; i++) {
			// find current code - hash #
			current = (checkFirst + i) % S.length;
			
			// get partitions of remaining codes
			int[] partition = getPartition(current);
			
			// get scores of partition list
			curScores = getScores(partition);
			
			if(bestScores == null) {
				// first possibility
				bestScores = curScores;
			} else {
				// subsequent possibilities
				int comparison = compareScores(curScores, bestScores);
				if(comparison > 0) {
					// new record
					best = current;
					bestScores = curScores;
				} else if(comparison == 0) {
					// final tie-breaker: is-in S
					if(!S[best]) {
						best = current;
					}
				}
			}
		}
		
		return best;
	}
	
		
	protected void update(Pattern patt, Indicator ind) {
		int[] last = patt.getPattern();
		int current; // current code to be checked
		
		Indicator other = new Indicator();
		for (current = 0; current < S.length; current++) {
			if(S[current]) {
				int[] cur = Pattern.unHash(current, patternLength, colors);
				other.generateHint(cur, last);
				
				if(!ind.equals(other)) {
					S[current] = false;
				}
			}
		}
	}
	
	public int[] getPartition(int current) {
		int[] counts = new int[(int)(patternLength*(0.5*patternLength + 1.5))];
		int[] guess = Pattern.unHash(current, patternLength, colors);
		int code; // code to check
		
		// set counts to 0
		for(int j = 0; j < counts.length; j++) {
			counts[j] = 0;
		}
		
		// loop through possible codes
		for(code = 0; code < S.length; code++) {
			if(S[code]) {
				// find hint
				Indicator ind = new Indicator();
				int[] patt = Pattern.unHash(code, patternLength, colors);
				ind.generateHint(patt, guess);
				
				// catalog which hint is generated
				counts[ind.hash(patternLength)]++;
			}
		}
		
		return counts;
	}
	
	/* Compares two score lists and returns a integer representing
	 * comparison. Positive means score1 is better, 
	 * negative means score2 is better, and zero means they are
	 * equal.
	 */
	protected int compareScores(double[] score1, double[] score2) {
		for(int i = 0; i < score1.length && i < score2.length; i++) {
			if(score1[i] > score2[i]) {
				return 1;
			} else if(score1[i] < score2[i]) {
				return -1;
			}
		}
		return 0;
	}
	
	
	/* Finds the score of the current guess given its partition.
	 * Returns: an array of scores; only the first score is required
	 * and all subsequent scores are tie-breakers in the event that
	 * all previous tie.
	 */
	public abstract double[] getScores(int[] partition);
	
	
	/////////// useful methods /////////////
	public static int sumUnsignedIntArray(int[] a) {
		int sum = 0;
		for(int i : a) {
			sum += i;
		}
		return sum;
	}
	
	public static int maxUnsignedIntArray(int[] a) {
		int max = -1;
		for(int value : a) {
			max = Math.max(value, max);
		}
		return max;
	}
	
	public static int minUnsignedIntArray(int[] a) {
		int min = -1;
		if(a.length > 0) {
			for(int value : a) {
				min = Math.min(value, min);
			}
		}
		return min;
	}
	
	public static int countNonzero(int[] a) {
		int count = 0;
		for(int i : a) {
			if(i != 0) {
				count++;
			}
		}
		return count;
	}
}
