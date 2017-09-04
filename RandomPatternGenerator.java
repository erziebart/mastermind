package main;

public class RandomPatternGenerator implements CodeMaker, CodeBreaker{
	
	private int patternLength;
	private int colors;
	
	public RandomPatternGenerator(int patternLength, int colors) {
		this.patternLength = patternLength;
		this.colors = colors;
	}
	
	@Override
	public int[] makeCode() {
		return getRandom();
	}

	@Override
	public int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints) {
		return getRandom();
	}
	
	private int[] getRandom() {
		int[] pegs = new int[patternLength];
		for (int i = 0; i < patternLength; i++) {
			pegs[i] = (int)Math.floor((Math.random()*colors));
		}
		return pegs;
	}
}
