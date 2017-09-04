package main;

public class MastermindGame {
	
	private int patternLength;
	private int maxGuesses;
	private int score;
	
	private CodeBreaker cb;
	private CodeMaker cm;
	
	private Pattern code;
	private Pattern[] guesses;
	private Indicator[] hints;
	

	public static void main(String[] args) {
		int patternLength = 5;
		int colors = 8;
		int maxGuesses = 12;
		Player p = new Player(patternLength);
		RandomPatternGenerator r = new RandomPatternGenerator(patternLength,colors);
		MastermindGame mg = new MastermindGame(p,r, patternLength, maxGuesses);
		mg.play();
	}
	
	public MastermindGame(CodeBreaker cb, CodeMaker cm, int patternLength, int maxGuesses) {
		this.patternLength = patternLength;
		this.maxGuesses =  maxGuesses;
		this.score = 1;
		this.cb = cb;
		this.cm = cm;
		code = null;
		guesses = new Pattern[maxGuesses];
		hints = new Indicator[maxGuesses];
	}
	
	public void play() {
		int turn = 0;
		boolean correct = false;
		code = new Pattern(cm.makeCode());
		
		while (turn < maxGuesses && !correct) {
			int[] nextGuess = cb.makeGuess(turn, guesses, hints);
			correct = guess(turn, nextGuess);
			turn++;
			
			// alert observer types
			if(cm instanceof Observer) {
				Observer ob = (Observer)cm;
				ob.alert(correct, turn);
			}
			if(cb instanceof Observer) {
				Observer ob = (Observer)cb;
				ob.alert(correct, turn);
			}
		}
		score = turn;
		if(!correct)
			score++;
	}
	
	public boolean doTurn(int turn) {
		boolean correct = false;
		
		// make code
		if(turn == 0) {
			code = new Pattern(cm.makeCode());
		}
		
		// make guess
		int[] nextGuess = cb.makeGuess(turn, guesses, hints);
		correct = guess(turn, nextGuess);
		
		// is correct
		if(correct)
			score = turn;
		
		return correct;
	}
	
	public boolean guess(int turn, int[] guess) {
		guesses[turn] = new Pattern(guess);
		hints[turn] = new Indicator();
		hints[turn].generateHint(code.getPattern(), guess);
		if (hints[turn].getRed() == patternLength) { // correct guess
			return true;
		}
		return false;
	}
	
	public int getScore() {
		return score;
	}
	
	public Pattern getCode() {
		return code;
	}
	
	public void setCode(Pattern code) {
		this.code = code;
	}
	
	public Pattern[] getGuesses() {
		return guesses;
	}
	
	public Indicator[] getHints() {
		return hints;
	}
}
