package main;

public class Indicator {
	private int red, white;
	
	public Indicator() {
		initHint();
	}
	
	private void initHint() {
		red = white = 0;
	}
	
	public int getRed() {
		return red;
	}
	
	public int getWhite() {
		return white;
	}
	
	public void generateHint(int[] code, int[] guess) {
		initHint();
		
		int[] temp = new int[code.length];
		int ref = 0;
		
		// check for color of each peg in code
		for(int i = 0; i < code.length; i++) {
			// check that color is not in temp
			boolean isInTemp = false;
			for(int t = 0; t < ref; t++) {
				if(code[i] == temp[t]) {
					isInTemp = true;
				}
			}
			
			if (!isInTemp) {
				// add to temp
				temp[ref] = code[i];
				ref++;
				
				// count instances of color in code and guess
				int codeCount, guessCount;
				codeCount = guessCount = 0;
				for(int j = 0; j < guess.length; j++) {
					if (code[i] == guess[j] && code[i] == code[j]) {
						red++;
					} else if(code[j] == code[i]) {
						codeCount++;
					} else if(guess[j] == code[i]) {
						guessCount++;
					}
				}
				
				// set white values
				if(codeCount >= guessCount) {
					white+= guessCount;
				} else {
					white+= codeCount;
				}
			}
		}
	}
	
	@Override
	public boolean equals(Object other) {
		// other object
		if(!(other instanceof Indicator)) {
			System.out.println("not instance");
			return false;
		}
		Indicator o = (Indicator)other;
						
		if(white == o.white && red == o.red) {
			return true;
		}
		return false;
	}
	
	public int hash(int numSlots) {
		// just trust me on this
		int hash = (int)(red*(-0.5*red+1.5+numSlots))+white;
		if(hash == numSlots*(0.5*numSlots+1.5))
			hash--; // fills non-possible hash
		return hash;
	}
}
