package project1;

import main.MastermindGame;

/* Estimates the average score of AIs by playing the game for set
 * number of trials - for non-deterministic or random algorithms
 */

public class ExperimentA {
	
	private static int trials = 5000;
	
	public static void main(String args[]) {
		int sum = 0;
		
		int patternLength = 4;
		int colors = 6;
		int maxGuesses = 12;
		AIKnuth p1 = new AIKnuth(patternLength, colors);
		AIIncrement p2 = new AIIncrement(patternLength, colors); 
		MastermindGame mg = new MastermindGame(p1, p2, patternLength, maxGuesses);
		
		for(int i = 0; i < trials; i++) {
			System.out.println("Trial: " + (i+1));
			p1.initTable();
			mg.play();
			sum += mg.getScore();
		}
		
		System.out.println("Average = " + (double)sum/trials);
	}
}
