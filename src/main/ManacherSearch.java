/**
 * Manacher's Algorithm for finding palindromes in a string.
 * See @buildAuxiliaryArray for more information.
 *
 */

package main;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class ManacherSearch implements PalindromeSearchAlgorithm {

	private int[] lengths;
	private static final String INPUT_PATTERN = "[A-Za-z0-9]+";

	/**
	 * Check whether input string has non-alphanumeric characters.
	 * 
	 * @param input
	 */
	private void validateInput(String input) {
		if (!input.matches(INPUT_PATTERN)) {
			throw new IllegalArgumentException(
					"Only letters and numbers are allowed.");
		}
	}

	/**
	 * Preprocess the original string to separate all letters with a #. Also add
	 * $ at the start and @ at the end of the string to avoid having to bound
	 * check. For example, "aba" -> "$#a#b#a#@". Preprocessing helps deal with
	 * odd- and even-length palindromes in the same way.
	 * 
	 * @param input
	 * @return processed string as char[]
	 */
	public char[] preprocess(String input) {

		char[] processed = new char[input.length() * 2 + 3];

		processed[0] = '$';
		processed[processed.length - 2] = '#';
		processed[processed.length - 1] = '@';

		for (int i = 0; i < input.length(); i++) {
			processed[2 * i + 1] = '#';
			processed[2 * i + 2] = input.charAt(i);
		}
		return processed;
	}

	/**
	 * The longest palindrome search algorithm. This uses Manacher's search as
	 * follows. We can exploit the symmetric property of palindromes to
	 * efficiently find new palindromes "to the right" of the palindromes that
	 * we've already found.
	 * 
	 * @param input
	 *            The input string.
	 * @return An array of integers that contains the maximum length of a
	 *         palindrome centered at that index.
	 */
	public int[] buildAuxiliaryArray(String input) {

		/*
		 * We use a reference palindrome, centered at center, to find out the
		 * length of a new palindrome, centered to the right of center, at i.
		 * 
		 * Right is the rightmost index of the reference palindrome (at center).
		 * Mirror is the mirror index of i with respect to center.
		 * 
		 * From now on we call palindrome(i) the palindrome centered at i and so
		 * on.
		 * 
		 * The length of palindrome(i) is either the length of
		 * palindrome(mirror) if palindrome(mirror) is fully contained in
		 * palindrome(center) or at least the distance from i to right.
		 * 
		 * Because of the "at least", we have to check whether we can find a
		 * longer palindrome(i), that extends further to the right. We keep
		 * going until we can no longer expand.
		 * 
		 * Then if we found a new longer palindrome, we have to adjust the
		 * center to account for the new palindrome.
		 * 
		 * Worst-case complexity of this search is O(length of input).
		 */

		validateInput(input);
		char[] processed = preprocess(input);

		lengths = new int[processed.length];

		int center = 0, right = 0;
		for (int i = 1; i < processed.length - 1; i++) {
			int mirror = 2 * center - i;

			if (right > i) {
				lengths[i] = Math.min(right - i, lengths[mirror]);
			}

			// try to expand palindrome(i)
			while (processed[i + (1 + lengths[i])] == processed[i
					- (1 + lengths[i])]) {
				lengths[i]++;
			}
			// if palindrome(i) extends past right, move center to i
			if (i + lengths[i] > right) {
				center = i;
				right = i + lengths[i];
			}
		}
		return lengths;
	}

	/**
	 * This methods uses the auxiliary array constructed by Manacher's Algorithm
	 * to find and sort all palindromes in the input string.
	 * 
	 * @param input
	 *            The input string
	 * @return List<Palindrome> List of palindromes, sorted by length in
	 *         descending order.
	 */

	@Override
	public List<Palindrome> findLongestUnique(String input, int n) {

		/*
		 * This method loops through the lengthsArray built by Manacher's
		 * algorithm and adds all the eligible palindromes (longer than 1 and
		 * unique) in a sorted set.
		 * 
		 * It then takes takes the first n palindromes and returns them.
		 */
		int[] lengthsArray = buildAuxiliaryArray(input);

		TreeSet<Palindrome> palindromes = new TreeSet<Palindrome>();

		for (int i = 0; i < lengthsArray.length; i++) {
			int len = lengthsArray[i];

			// only consider palindromes of length bigger than 1
			if (len > 1) {
				int beginIndex = (i - 1 - len) / 2;
				int endIndex = (i - 1 + len) / 2;
				Palindrome pal = new Palindrome(input.substring(beginIndex,
						endIndex), len, beginIndex);
				palindromes.add(pal);
			}
		}

		return (new LinkedList<Palindrome>(palindromes)).subList(0,
				Math.min(n, palindromes.size()));

	}

}
