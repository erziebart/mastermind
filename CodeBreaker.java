package main;

public interface CodeBreaker {
	int[] makeGuess(int turn, Pattern[] guesses, Indicator[] hints);
}
