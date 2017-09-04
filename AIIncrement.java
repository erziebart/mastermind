package project1;

import main.Indicator;
import main.Pattern;
import main.RandomPatternGenerator;

public class AIIncrement extends main.TestAI {

	public AIIncrement(int patternLength, int colors) {
		super(patternLength, colors);
		
	}

	@Override
	public int[] makeCode() {
		RandomPatternGenerator r;
		r = new RandomPatternGenerator(patternLength, colors);
		return r.makeCode();
	}

	@Override
	public int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints) {
		int[] guess;
		
		if(turn == 0) { // first guess
			guess = makeCode();
						
		// add 1 to last guess
		} else {
			Pattern p = new Pattern(guesses[turn-1].copyColors());
			p.increment(colors);
			guess = p.getPattern();
		}
		return guess;
	}
}
