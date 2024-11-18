package wordle.view;

import javax.swing.JOptionPane;

import wordle.model.Game;

/**
 * Class for pop up messages
 */
public class MessageBox extends JOptionPane {
	// Main model of the game
	private Game model;

	/**
	 * Construct the message box
	 * 
	 * @param model main model of the game
	 */
	public MessageBox(Game model) {
		this.model = model;
	}

	/**
	 * Update Message box when there are changes to the model
	 */
	public void update() {
		String message = model.getMessage();
		this.showMessageDialog(null, message);
	}

}
