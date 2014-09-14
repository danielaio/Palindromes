/**
 * PalindromesApp reports the 3 longest unique palindromes in a 
 * string, as well as the start index and length for each of 
 * the palindromes in descending order.
 * 
 * To run PalindromesApp, pass in the input strings to be searched 
 * as command line arguments.
 */

package main;

import java.util.List;

public class PalindromeApp {

	public static void main(String args[]) {

		PalindromeSearchAlgorithm algo = new ManacherSearch();

		int topN = 3;

		for (String s : args) {
			List<Palindrome> longest = algo.findLongestUnique(s, topN);

			System.out.println("Results for input " + s);
			for (Palindrome pal : longest) {
				System.out.println(pal);
			}
		}
	}
}
