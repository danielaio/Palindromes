/**
 * Black-box tests for a PalindromeSearchAlgorithm. 
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import main.ManacherSearch;
import main.Palindrome;
import main.PalindromeSearchAlgorithm;

import org.junit.Before;
import org.junit.Test;

public class PalindromeSearchTest {

	private PalindromeSearchAlgorithm search;
	private int n;

	@Before
	public void setUp() {
		search = new ManacherSearch();
		n = 3;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmpty() {
		search.findLongestUnique("", n);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonAlphaNumeric() {
		search.findLongestUnique("#[]];#';", n);
	}

	@Test
	public void testNBiggerThanNumberOfPalindromes() {
		String input = "aba";
		int n = 10;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		assertEquals(1, pals.size());
	}

	@Test
	public void testNoPalindromes() {
		String input = "nopals";
		int n = 10;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		assertEquals(0, pals.size());
	}

	@Test
	public void testSingleLetter() {
		String input = "n";
		int n = 10;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		assertEquals(0, pals.size());
	}

	@Test
	public void testTwoLetters() {
		String input = "aa";
		int n = 3;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		assertEquals(1, pals.size());
	}

	
	@Test
	public void testNotUnique() {
		String input = "aaaa";
		int n = 10;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		assertEquals(3, pals.size());
	}

	@Test
	public void testOverlapping() {
		String input = "abbaabba";
		int n = 10;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		assertEquals(2, pals.size());
	}

	@Test
	public void testExample() {
		String input = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop";
		int n = 3;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		List<Palindrome> expected = new ArrayList<Palindrome>();
		expected.add(new Palindrome("hijkllkjih", 10, 23));
		expected.add(new Palindrome("defggfed", 8, 13));
		expected.add(new Palindrome("abccba", 6, 5));
		for (int i = 0; i < pals.size(); i++) {
			assertTrue(pals.get(i).comparePalindromes(expected.get(i)));
		}
	}
	
	@Test
	public void testWithOverlappingAndNonOverlapping() {
		String input = "abcacbbbca";
		int n = 3;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		List<Palindrome> expected = new ArrayList<Palindrome>();
		expected.add(new Palindrome("acbbbca", 7, 3));
		expected.add(new Palindrome("bcacb", 5, 1));
		expected.add(new Palindrome("bb", 2, 5));
		
		for (int i = 0; i < pals.size(); i++) {
			assertTrue(pals.get(i).comparePalindromes(expected.get(i)));
		}
	}
	
	@Test
	public void testRaceCar() {
		String input = "racecar";
		int n = 3;
		List<Palindrome> pals = search.findLongestUnique(input, n);
		List<Palindrome> expected = new ArrayList<Palindrome>();
		expected.add(new Palindrome("racecar", 7, 0));
		
		for (int i = 0; i < pals.size(); i++) {
			assertTrue(pals.get(i).comparePalindromes(expected.get(i)));
		}
	}
}
