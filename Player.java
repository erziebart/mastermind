package main;

import java.util.Scanner;

public class Player implements CodeMaker, CodeBreaker, Observer {
	Scanner input;
	
	private int patternLength;
	
	public Player(int patternLength) {
		this.patternLength = patternLength;
		input = new Scanner(System.in);
	}
	
	@Override
	public int[] makeCode() {
		System.out.println("Create a Code: ");
		return inputPattern();
	}
	
	@Override
	public int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints) {
		printBoard(guesses, hints);
		System.out.println("Make a Guess: ");
		return inputPattern();
	}
	
	private void printBoard(Pattern[] guesses, Indicator[] hints) {
		for(int i = 0; i < guesses.length; i++) {
			if(guesses[i] != null) {
				int[] next = guesses[i].getPattern();
				System.out.print("Turn " + (i+1) + ": ");
				for (int j = 0; j < next.length; j++) {
					System.out.print(next[j] + ", ");
				}
				System.out.print("Red: " + hints[i].getRed());
				System.out.print(" White: " + hints[i].getWhite());
				System.out.println();
			}
		}
	}

	private int[] inputPattern() {
		input.reset();
		int[] temp = new int[patternLength];
		int ref = 0;
		//input.useDelimiter(",");
		for(int i = 0; i < patternLength; i++) {
			if(input.hasNext()) {
				temp[ref] = input.nextInt();
				ref++;
			}
		}
		
		return temp;
	}

	@Override
	public void alert(boolean gameOver, int score) {
		if(gameOver) {
			System.out.println("Correct!");
			System.out.println("Score = " + score);
		}
	}
}
