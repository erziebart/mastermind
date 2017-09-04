package project1;

import main.*;

/* This class prints each step in a game */
public class ExperimentC {
	
	public static void main(String[] args) {
		int length = 4;
		int colors = 6;
		int maxGuesses = 10;
		int checkFirst = 500;
		
		int[] code = Pattern.unHash(checkFirst, length, colors); // code to guess
		int[] first = {1,1,2,2}; // first guess
		
		algorithms.SListAI p1 = new algorithms.Knuth(length, colors);
		FixedPatternGenerator p2 = new FixedPatternGenerator(length); 
		MastermindGame mg = new MastermindGame(p1, p2, length, maxGuesses);
		
		p2.setPattern(code);
		p1.setFirstGuess(first);
		p1.setCheckFirst(1);
		
		mg.play();
		Pattern[] guesses = mg.getGuesses();
		
		System.out.println("Guesses: ");
		int i = 0;
		while(i < maxGuesses && guesses[i] != null) {
			for(int n : guesses[i].getPattern()) {
				System.out.print(n + ", ");
			}
			System.out.println();
			i++;
		}
	}

}
