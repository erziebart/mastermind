package project1;

import main.*;

public class Tester {

	public static void main(String[] args) {
		int patternLength = 4;
		int colors = 6;
		int length = 5;
		
		AIKnuth p1 = new AIKnuth(patternLength,colors);
		AIKnuth p2 = new AIKnuth(patternLength,colors);
		MastermindGame mg = new MastermindGame(p1,p2,patternLength,length);
		
		int turn = 0;
		mg.doTurn(turn);
		System.out.print("Code: ");
		Tester.printIntArray(mg.getCode().getPattern());
		System.out.print("Guess #" + (turn+1) + ": ");
		Tester.printIntArray(mg.getGuesses()[turn].getPattern());
		
		while(turn < length-1) {
			turn++;
			System.out.print("Guess #" + (turn+1) + ": ");
			Pattern next = new Pattern(p1.makeGuess(turn, mg.getGuesses(), mg.getHints()));
			Tester.printIntArray(next.getPattern());
			System.out.print("Counts #" + (turn+1) + ": ");
			Tester.printIntArray(p1.getCounts(Pattern.hash(next.getPattern(), colors)));
			mg.doTurn(turn);
		}
	}
	
	public static void printIntArray(int[] a) {
		for(int i : a) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

}
