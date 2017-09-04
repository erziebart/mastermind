package main;

public abstract class TestAI implements CodeMaker, CodeBreaker {
	protected int patternLength;
	protected int colors;
	
	public TestAI(int patternLength, int colors) {
		this.patternLength = patternLength;
		this.colors = colors;
	}
}
