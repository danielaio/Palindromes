/**
 * Class to represent a palindrome. Palindromes are ordered 
 * by length in descending order. The string representation of a 
 * palindrome is the text, followed by the start index, 
 * followed by the length of the palindrome.
 * Two palindromes are the same if they contain the same text.
 */

package main;

public class Palindrome implements Comparable<Palindrome> {

	private String palindrome;
	private int length;
	private int beginIndex;

	public Palindrome(String pal, int length, int beginIndex) {
		if (length <= 1 || beginIndex < 0)
			throw new IllegalArgumentException();
		this.palindrome = pal;
		this.length = length;
		this.beginIndex = beginIndex;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("");
		b.append("Text: ").append(this.palindrome).append(", Index: ")
				.append(this.beginIndex);
		b.append(" , Length: ").append(this.length);
		return b.toString();
	}


	@Override
	public int compareTo(Palindrome other) {
		if (this.equals(other)) {
			return 0;
		} else if (this.length == other.length) {
			return 1;
		} else if (this.length >= other.length) {
			return -1;
		} else {
			return 1;
		}

	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass()) {
			return false;
		} else {
			Palindrome otherPal = (Palindrome) other;
			if (otherPal.palindrome.equals(this.palindrome))
				return true;
		}
		return false;
	}

	/**
	 * Use this method for testing. It compares both the string and the beginIndex.
	 * @param other
	 * @return
	 */
	public boolean comparePalindromes(Palindrome other) {
		if (this.getBeginIndex() == other.getBeginIndex() && 
				this.getPalindrome().equals(other.getPalindrome())) {
			return true;			
		}
		return false;
	}
	
	public String getPalindrome() {
		return palindrome;
	}

	public int getLength() {
		return length;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public int getEndIndex() {
		return beginIndex + length;
	}

}
