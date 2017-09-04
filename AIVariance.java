package project1;

import main.Indicator;
import main.Pattern;
import main.RandomPatternGenerator;

public class AIVariance extends main.TestAI {
	
	boolean[] patternPossible;

	public AIVariance(int patternLength, int colors) {
		super(patternLength, colors);
		patternPossible = new boolean[(int)Math.pow(colors, patternLength)];
		initTable();
	}
	
	public void initTable() {
		for(int i = 0; i < patternPossible.length; i++) {
			patternPossible[i] = true;
		}
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
			return makeCode();
						
		} else {
			// update possibility table
			updateTable(guesses[turn-1], hints[turn-1]);
			
			//////find guess with lowest variance//////
			int[] init = new int[patternLength];
			for(int i = 0; i < patternLength; i++) {
				init[i] = 0;
			}
			
			// winner choices
			double bestVar = patternPossible.length;
			double curVar = bestVar;
			Pattern best = new Pattern(init);
			
			// number of unique possible hints
			int[] counts = new int[(int)(patternLength*(0.5*patternLength + 1.5))];
			
			// loop through all possible guesses
			Pattern current = new Pattern(best.copyColors());
			for(int i = 0; i < patternPossible.length; i++) {
				// set counts to 0
				for(int j = 0; j < counts.length; j++) {
					counts[j] = 0;
				}
				
				// loop through remaining possible codes
				Pattern code = new Pattern(current.copyColors());
				for(int j = 0; j < patternPossible.length; j++) {
					if(patternPossible[Pattern.hash(code.getPattern(), colors)]) {
						
						// find hint
						Indicator ind = new Indicator();
						ind.generateHint(code.getPattern(), current.getPattern());
						
						// catalog which hint is generated
						counts[ind.hash(patternLength)]++;
					}
					
					code.increment(colors);
				}
				
				// find average patterns per hint
				int s = 0;
				for(int num : counts) {
					s += num;
				}
				double average = (double)s/counts.length;
				
				// code found
				if(s == 1 && counts[counts.length - 1] == 1) {
					bestVar = 0;
					best = new Pattern(current.copyColors());
				}
				
				// find variance
				double sum = 0;
				for(int num : counts) {
					sum += (num - average)*(num - average);
				}
				curVar = sum/counts.length;
				
				// determine if new winner
				if(curVar < bestVar) {
					best = new Pattern(current.copyColors());
					bestVar = curVar;
				}
				
				current.increment(colors);
			}
			
			return best.getPattern();
		}
	}

	private void updateTable(Pattern pattern, Indicator indicator) {
		Pattern current = new Pattern(pattern.copyColors());
		
		Indicator ind = new Indicator();
		for (int i = 0; i < patternPossible.length; i++) {
			int ref = Pattern.hash(current.getPattern(), colors);
			if(patternPossible[ref]) {
				ind.generateHint(pattern.getPattern(), current.getPattern());
				
				if(!ind.equals(indicator)) {
					patternPossible[ref] = false;
				}
			}
			
			current.increment(colors);
		}
		
	}

}
