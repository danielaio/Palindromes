/**
 * A palindrome search algorithm will find palindromes in a string.
 * Only palindromes of length bigger than 1 are considered.
 * If one palindrome is contained within another palindrome and 
 * both are centred at the same location, only the bigger one is reported.
 * 
 */

package main;

import java.util.List;

public interface PalindromeSearchAlgorithm {

	/**
	 * Return the n longest unique palindromes in the input string.
	 * 
	 * If n is bigger than the total number of palindromes in the string, return
	 * all palindromes found. If no palindromes are found, an empty list is
	 * returned.
	 * 
	 * If a palindrome appears more than once, it's first occurrence is
	 * reported.
	 * 
	 * @param input
	 *            Input string
	 * @param n
	 *            Number of palindromes to return
	 * @throws IllegalArgumentException
	 *             for non-alphanumeric characters
	 * @return A list containing the n longest palindromes in the string.
	 */
	List<Palindrome> findLongestUnique(String input, int n)
			throws IllegalArgumentException;
}
