/**
 * White-box tests for the ManacherSearch algorithm.
 *
 */

package test;

import static org.junit.Assert.assertArrayEquals;
import main.ManacherSearch;

import org.junit.Before;
import org.junit.Test;


public class ManacherSearchTest {

	private ManacherSearch search;
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
	public void testPreprocess() {
		String input = "aba";
		String processed = "$#a#b#a#@";
		assertArrayEquals(processed.toCharArray(), search.preprocess(input));
	}
	
	@Test
	public void buildAuxiliaryArray() {
		String input = "abaaba";
		assertArrayEquals(new int[]{0, 0, 1, 0, 3, 0, 1, 6, 1, 0, 3, 0, 1, 0, 0}, search.buildAuxiliaryArray(input));
	}
	
	@Test
	public void buildAuxiliaryArraySingleton() {
		String input = "a";
		assertArrayEquals(new int[]{0, 0, 1, 0, 0}, search.buildAuxiliaryArray(input));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buildAuxiliaryArrayEmpty() {
		String input = "";
		search.buildAuxiliaryArray(input);
	}	
	
	@Test
	public void buildAuxiliaryArrayRaceCar() {
		String input = "racecar";
		assertArrayEquals(new int[]{0, 0, 1, 0, 1, 0, 1, 0, 7, 0, 1, 0, 1, 0, 1, 0, 0}, search.buildAuxiliaryArray(input));
		
	}
}
