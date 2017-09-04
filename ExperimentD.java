package project1;

import main.FixedPatternGenerator;
import main.MastermindGame;

/* Prints the partitions in a specific situation */
public class ExperimentD {
	public static void main(String[] args) {
		int length = 4;
		int colors = 6;
		int maxGuesses = 10;
		
		int[] group0 = {625, 0, 0, 0, 0, 500, 0, 0, 0, 150, 0, 0, 20, 1}; // XXXX - 511.9799383
		int[] group1 = {256, 308, 61, 0, 0, 317, 156, 27, 0, 123, 24, 3, 20, 1}; //XXXY - 235.9490741
		int[] group2 = {256, 256, 96, 16, 1, 256, 208, 36, 0, 114, 32, 4, 20, 1}; //XXYY - 204.5354938
		int[] group3 = {81, 276, 222, 44, 2, 182, 230, 84, 4, 105, 40, 5, 20, 1}; //XXYZ - 185.2685185
		int[] group4 = {16, 152, 312, 136, 9, 108, 252, 132, 8, 96, 48, 6, 20, 1}; //XYZW - 188.1898148
		
		algorithms.SListAI p1 = new algorithms.Knuth(length, colors);
		FixedPatternGenerator p2 = new FixedPatternGenerator(length); 
		MastermindGame mg = new MastermindGame(p1, p2, length, maxGuesses);
		
		for(int i = 0; i < (int)Math.pow(colors, length); i++) {
			int[] partition = p1.getPartition(i);
			if(compareIntArr(partition, group0) != 0
			&& compareIntArr(partition, group1) != 0
			&& compareIntArr(partition, group2) != 0
			&& compareIntArr(partition, group3) != 0
			&& compareIntArr(partition, group4) != 0) {
				System.out.print("Code " + i + ": ");
				for(int n : partition) {
					System.out.print(n + ", ");
				}
				System.out.println();
			}
		}
	}
	
	public static int compareIntArr(int[] a1, int[] a2) {
		for(int i = 0; i < a1.length && i < a2.length; i++) {
			if(a1[i] > a2[i]) {
				return 1;
			} else if(a1[i] < a2[i]) {
				return -1;
			}
		}
		return 0;
	}
}
