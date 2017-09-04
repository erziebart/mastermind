package project1;

import main.Indicator;
import main.Pattern;
import main.RandomPatternGenerator;
import main.TestAI;

public class AIKnuth extends TestAI {
	
	private boolean[] S;
	private int[] firstGuess;

	public AIKnuth(int patternLength, int colors) {
		super(patternLength, colors);
		S = new boolean[(int)Math.pow(colors, patternLength)];
		initTable();
	}
	
	public void initTable() {
		for(int i = 0; i < S.length; i++) {
			S[i] = true;
		}
	}
	
	public void setFirstGuess(int[] pattern) {
		firstGuess = pattern;
	}

	@Override
	public int[] makeCode() {
		RandomPatternGenerator r;
		r = new RandomPatternGenerator(patternLength, colors);
		return r.makeCode();
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
			
			// find best guess using Knuth algorithm - 1977
			int bestGuess = 0;
			int bestScore = S.length; 
			int curScore;
			int current; // current pattern - hash #
			for(current = 0; current < S.length; current++) {
				int[] counts = getCounts(current);
				
				// set max to score
				curScore = maxOfIntArray(counts);
				
				// find new records
				if(curScore < bestScore) {
					bestGuess = current;
					bestScore = curScore;
				} else if(curScore == bestScore) {
					// is-in-S tie break
					if(!S[bestGuess]) {
						bestGuess = current;
					}
				}
			}
			
			return Pattern.unHash(bestGuess, patternLength, colors);
		}
	}

	private void update(Pattern patt, Indicator ind) {
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
		
	 int[] getCounts(int current) {
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
	
	private int maxOfIntArray(int[] counts) {
		int max = -1;
		for(int value : counts) {
			max = Math.max(value, max);
		}
		return max;
	}
}
