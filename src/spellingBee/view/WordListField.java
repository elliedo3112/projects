package spellingBee.view;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import spellingBee.model.WordList;

/**
 * The WordListField is an area that displays users correct words in
 * alphabetical order and count of user words
 */
public class WordListField extends JTextPane implements WordListListener {

    private WordList list = new WordList();

    // private WordCount wordCount;// = new WordCount();

    /**
     * @param height   the list area height
     * @param width    the list are width
     * @param wordList the list of users correct words to be displayed
     */
    public WordListField(int height, int width, WordList wordList) {
        super();
        setEditable(false); // Ensure the field is not editable by the user
        setBorder(BorderFactory.createEtchedBorder());
        this.list = wordList;

    }

    // @Override
    /**
     * Updates the word list area when new correct words are added.
     */
    public void update() {

        // create a custom StyledDocument
        StyledDocument doc = getStyledDocument();

        // Create bold Style text
        Style style = doc.addStyle("pangram", null);
        StyleConstants.setBold(style, true);

        setText("");
        for (int i = 0; i < list.getWordList().size(); i++) {
            if (list.getWordList().get(i).getPangram() == true) {

                try {

                    doc.insertString(doc.getLength(),
                            list.getWordList().get(i).getWord() + "\n", style);
                } catch (BadLocationException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                }
            } else {

                try {

                    doc.insertString(doc.getLength(),
                            list.getWordList().get(i).getWord() + "\n", null);

                } catch (BadLocationException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();

                }
            }

        }
    }
}
