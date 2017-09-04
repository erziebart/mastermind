package main;

public class Pattern {
	private int[] pegs;
	
	public Pattern(int[] pegs) {
		setPattern(pegs);
	}
	
	public Pattern(int hashCode, int length, int numColors) {
		setPattern(Pattern.unHash(hashCode, length, numColors));
	}
	
	public void setPattern(int[] pegs) {
		this.pegs = pegs;
	}
	
	public int[] getPattern() {
		return pegs;
	}
	
	public int getSize() {
		return pegs.length;
	}
	
	@Override
	public boolean equals(Object other) {
		// other object
		if(!(other instanceof Pattern)) {
			return false;
		}
		Pattern o = (Pattern)other;
		
		// diff length?
		if(pegs.length != o.pegs.length) {
			return false;
		}
		
		// same colors?
		for(int i = 0; i < pegs.length; i++) {
			if(pegs[i] != o.pegs[i]) {
				return false;
			}
		}
		return true;
		
	}
	
	public int[] copyColors() {
		int[] copy = new int[pegs.length];
		for(int i = 0; i < pegs.length; i++) {
			copy[i] = pegs[i];
		}
		return copy;
	}
	
	public void increment(int numColors) {
		// find place
		int place = pegs.length - 1;
		while(place >= 0 && pegs[place] == numColors-1) {
			pegs[place] = 0;
			place--;
		}
		
		// check for overflow
		if(place >= 0) {
			pegs[place]++;
		}
	}
	
	public static int hash(int[] pattern ,int numColors) {
		int hashCode = 0;
		for(int i = 0; i < pattern.length; i++) {
			hashCode += pattern[i]*Math.pow(numColors, pattern.length - 1 - i);
		}
		return hashCode;
	}
	
	public static int[] unHash(int hashCode, int length, int numColors) {
		int[] value = new int[length];
		for(int i = 0; i < length; i++) {
			int placeValue = (int)Math.pow(numColors, length - 1 - i);
			value[i] = hashCode / placeValue;
			hashCode -= value[i] * placeValue;
		}
		
		return value;
	}
}
