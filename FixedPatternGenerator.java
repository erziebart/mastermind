package main;

public class FixedPatternGenerator implements CodeMaker, CodeBreaker {
	int[] pattern;
	
	public FixedPatternGenerator(int length) {
		pattern = new int[length];
	}
	
	public void setPattern(int[] pattern) {
		this.pattern = pattern;
	}

	@Override
	public int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints) {
		return pattern;
	}

	@Override
	public int[] makeCode() {
		return pattern;
	}
}
