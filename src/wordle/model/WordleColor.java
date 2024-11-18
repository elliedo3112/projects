package wordle.model;

import java.awt.Color;

/**
 * Class that manages prominent colors in the game
 */
public class WordleColor {
	// The color for when the letter is wrong and not contained
	public static Color GRAY = new Color(120, 120, 120);
	// The color for when the letter is in the right index
	public static Color GREEN = new Color(105, 170, 100);
	// The color for when the letter is in the wrong index but is contained
	public static Color YELLOW = new Color(200, 180, 100);
	// Original color for the key buttons in the keyboard
	public static Color WHITEBTN = new Color(222, 222, 222);
}
