package project1;

import main.Indicator;
import main.Pattern;
import main.TestAI;

public class AIH extends TestAI {
	
	private int currentColor;
	private boolean[][] patterns;
	private int[] colorsNum;

	public AIH(int patternLength, int colors) {
		super(patternLength, colors);
		currentColor = 0;
		patterns = new boolean[patternLength][colors];
		colorsNum = new int[colors];
		
		for(int i = 0; i < patternLength; i++) {
			for(int j = 0; j < colors; j++) {
				patterns[i][j] = true;
			}
		}
		
		for(int i = 0; i < colors; i++) {
			colorsNum[i] = 0;
		}
	}

	@Override
	public int[] makeCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints) {
		int[] guess = new int[patternLength];
		
		
		return null;
	}

}
