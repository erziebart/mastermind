package project1;

import main.*;

/* Finds exact expected value (average) of score by looping over each
 * of the possible starting codes - for deterministic or non-random
 * algorithms
 */
public class ExperimentB {

	public static void main(String[] args) {
		// testing this starting code
		int[] first = {1,1,4,4};
		
		int sum = 0;
		int maxScore = 0;
		
		int patternLength = 4;
		int colors = 6;
		int maxGuesses = 10;
		
		algorithms.SListAI p1 = new algorithms.Knuth(patternLength, colors);
		p1.setFirstGuess(first);
		FixedPatternGenerator p2 = new FixedPatternGenerator(patternLength); 
		MastermindGame mg = new MastermindGame(p1, p2, patternLength, maxGuesses);
		
		// testing this check first code
		p1.setCheckFirst(273);
		
		int trials = (int)Math.pow(colors, patternLength);
		
		for(int code = 0; code < trials; code++) {
			int[] current = Pattern.unHash(code, patternLength, colors);
			p2.setPattern(current);
			
			p1.init();
			mg.play();
			int score = mg.getScore();
			if(score > maxScore) {
				maxScore = score;
			}
			System.out.println("Code: " + code + " - Score: " + score);
			sum += score;
		}
		
		System.out.println("Average = " + (double)sum/trials);
		System.out.println("Max: " + maxScore);
	}

}
