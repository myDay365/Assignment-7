package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author ??
 */
public class BalancedSymbolChecker {

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. (Use the methods below for constructing messages.) Throws
	 * FileNotFoundException if the file does not exist.
	 */
	public static String checkFile(String filename) throws FileNotFoundException {
		MyStack<Character> stack = new MyStack<>();
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		boolean isComment = false,isQuotes1 = false,isQuotes2 = false, error = false;
		int lineNumber = 0;
		char curr_char = '\b';
		int i = 0;
		while (sc.hasNext() && !error) {
			lineNumber++;
			String check = sc.nextLine();
			i = 0;
			while ( i < check.length()){
				curr_char = check.charAt(i);
				if(isComment){
					if(check.charAt(i) == '*' && check.charAt(i+1) == '/'){
						isComment = false;
						i+=2;
					}
				}
				else if(isQuotes1){
					if(check.charAt(i) == '\\')
						i++;
					else if(check.charAt(i) == '\"'){
						isQuotes1 = false;
					}
				}
				else if(isQuotes2){
					if(check.charAt(i) == '\\')
						i++;
					if(check.charAt(i) == '\''){
						isQuotes2 = false;
					}
				}
				else {
					if (curr_char == '(' || curr_char == '{' || curr_char == '[') {
						stack.push(curr_char);
					} else if (curr_char == '/') {
						if (check.charAt(i + 1) == '/') {
							break;
						} else if (check.charAt(i + 1) == '*') {
							isComment = true;
							i += 2;
						}
					}
					else if(curr_char == '\"'){
						isQuotes1 = true;
						i+=1;
						continue;
					}
					else if(curr_char == '\''){
						isQuotes2 = true;
						i+=1;
						continue;
					}
					else if (curr_char == ')' || curr_char == '}' || curr_char == ']') {
						if (stack.peek() == null) {
							error = true;
							break;
						} else if (curr_char == ')' && stack.peek() == '(') {
							stack.pop();
						} else if (curr_char == '}' && stack.peek() == '{') {
							stack.pop();
						} else if (curr_char == ']' && stack.peek() == '[') {
							stack.pop();
						} else {
							error = true;
							break;
						}
					}
				}
				i++;
			}
		}
		if(isComment)
			return unfinishedComment();
		else if(stack.isEmpty())
			if (error)
				return unmatchedSymbol(lineNumber, i+1, curr_char, ' ');
			else
				return allSymbolsMatch();
		else{
			if(stack.size() == 1){
				return unmatchedSymbolAtEOF(expected(stack.peek()));
			}
			else {
				return unmatchedSymbol(lineNumber, i+1, curr_char, expected(stack.peek()));
			}
		}
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
	
	/**
	 * Use this to get the expected character
	 *
	 * @param c the character for which the expected value is to be found
	 * @return the expected character
	 */
	private static char expected(char c){
		if(c == '(')
			return ')';
		else if(c == ')')
			return '(';
		else if(c == '{')
			return '}';
		else if(c == '}')
			return '{';
		else if(c == '[')
			return ']';
		return '[';
	}
}
