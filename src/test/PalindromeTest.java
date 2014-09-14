package test;

import static org.junit.Assert.*;

import java.util.TreeSet;

import main.Palindrome;

import org.junit.Before;
import org.junit.Test;

public class PalindromeTest {

	TreeSet<Palindrome> actual;

	@Before
	public void setUp() {

		actual = new TreeSet<Palindrome>();
		actual.add(new Palindrome("bcacb", 5, 1));
		actual.add(new Palindrome("cac", 3, 2));
		actual.add(new Palindrome("bb", 2, 5));
		actual.add(new Palindrome("acbbbca", 7, 3));
		actual.add(new Palindrome("cbbc", 4, 4));
		actual.add(new Palindrome("bbb", 3, 5));
		actual.add(new Palindrome("bb", 2, 6));
		actual.add(new Palindrome("bbb", 3, 5));
		for (Palindrome pal : actual) {
			System.out.println(pal);
		}
	}

	@Test
	public void testPalindromeEquals() {
		assertEquals(6, actual.size());
	}

	@Test
	public void testSorting() {
		for (Palindrome p : actual) {
			Palindrome lower = actual.higher(p);
			if (lower != null) {
				assertTrue(p.getLength() >= lower.getLength());
			}
		}
	}

}
