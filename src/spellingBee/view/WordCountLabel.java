package spellingBee.view;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import spellingBee.model.WordCount;

/**
 * The WordCountLabel class updates the word count displayed on the label
 */
public class WordCountLabel extends JTextArea implements CountListener {

    // private SpellingBeeGame game;

    private WordCount count = new WordCount();

    public WordCountLabel(int height, int width, WordCount count) {

        super(height, width);
        setEditable(false);
        setBorder(BorderFactory.createEtchedBorder());
        this.count = count;
        setText("You have found 0 word");
    }

    /**
     * Update the text with the new count
     * 
     * @param count the number of users correct words
     */
    public void update() {
        setText("You have found " + count.getCount() + " words");

    }

}
