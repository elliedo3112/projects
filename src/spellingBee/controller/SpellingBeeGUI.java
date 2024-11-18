package spellingBee.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import spellingBee.model.SpellingBeeGame;
import spellingBee.view.Honeycomb;
import spellingBee.view.WordCountLabel;
import spellingBee.view.WordListField;

/**
 * This class provides a graphical user interface that allows a user to enter
 * words, shuffle letters in honeycomb, see number of words found and correct
 * words they have found.
 */
public class SpellingBeeGUI {

    private SpellingBeeGame game = new SpellingBeeGame();

    // the height and width of the word list field area
    private static final int LIST_HEIGHT = 500;
    private static final int LIST_WIDTH = 30;

    // the height and width of the word label field area
    private static final int LABEL_HEIGHT = 1;
    private static final int LABEL_WIDTH = 30;

    // where the correct words list appears on the screen
    private WordListField wordListArea = new WordListField(LIST_HEIGHT,
            LIST_WIDTH, game.getWordList());

    private JTextField entryField = new JTextField(15);

    private WordCountLabel wordCountLabel = new WordCountLabel(LABEL_HEIGHT,
            LABEL_WIDTH, game.getCount());

    private Honeycomb honeycomb;

    private JButton shuffleButton;

    // error messages
    private static final String NOT_IN_LIST_ERROR_MSG = "Not in word list";
    private static final String WORD_ERROR_MSG = "Already found";
    private static final String WORD_MISSING_ERROR_MSG = "Missing center letter";
    private static final String WORD_SHORT_ERRPR_MSG = "Too Short";
    private static final String WORD_INVALID_ERROR_MSG = "Invalid letters";

    /**
     * Constructs the user interface for the Spelling Bee program.
     */
    public SpellingBeeGUI() {

        // set up the game
        game.startGame();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // display honeycomb
        JPanel honeyCombPanel = new JPanel();
        honeyCombPanel.setBackground(Color.WHITE);
        honeyCombPanel
                .setLayout(new BoxLayout(honeyCombPanel, BoxLayout.Y_AXIS));
        honeyCombPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 85, 0));

        // Create honeycomb with letters in each cell
        honeycomb = new Honeycomb(game.getLetters(), game.getCenter());
        honeycomb.setPreferredSize(new Dimension(400, 400));

        // Creates button that triggers shuffling feature
        shuffleButton = new JButton("Shuffle");

        // align the shuffle button to the center horizontally
        shuffleButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adding honeycomb and shuffle button
        honeyCombPanel.add(honeycomb, BorderLayout.CENTER);
        honeyCombPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        honeyCombPanel.add(shuffleButton, BorderLayout.CENTER);

        // attach action listener to shuffleButton
        shuffleButton.addActionListener(event -> {

            char[] shuffledLetters = honeycomb
                    .shuffleLetters(game.getLetters());

            game.setLetters(shuffledLetters);

            honeycomb.setOuterLetters(game.getLetters());

            honeyCombPanel.repaint();

        });

        // panel to dispay list of correct words and word count
        JPanel wordListPanel = new JPanel(new BorderLayout());

        // create a JScrollPane
        JScrollPane wordListPane = new JScrollPane(wordListArea);
        wordListPane.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0));

        // add word list area and word count label
        wordListPanel.add(wordCountLabel, BorderLayout.NORTH);
        wordListPanel.add(wordListPane);// , BorderLayout.CENTER);

        // add listeners to count label and word list area
        game.getWordList().addListener(wordListArea);
        game.getCount().addListener(wordCountLabel);

        // panel for organizing input components
        JPanel inputPanel = new JPanel();

        // create user interface where users can input words
        inputPanel.add(new JLabel("Your word: "));
        inputPanel.add(entryField);

        // attach action listener to entryField
        entryField.addActionListener(event -> {
            String text = entryField.getText().toLowerCase();

            // clear the entryField
            entryField.setText("");

            // checks if word is valid
            int checkWordResult = game.checkWord(text, game.getLetters(),
                    game.getCenter());

            // print appropriate error messages based on the input word
            if (checkWordResult == 1) {
                JOptionPane.showMessageDialog(null, WORD_SHORT_ERRPR_MSG);
            } else if (checkWordResult == 2) {
                JOptionPane.showMessageDialog(null, WORD_MISSING_ERROR_MSG);
            } else if (checkWordResult == 3) {
                JOptionPane.showMessageDialog(null, WORD_INVALID_ERROR_MSG);
            } else if (checkWordResult == 4) {
                JOptionPane.showMessageDialog(null, NOT_IN_LIST_ERROR_MSG);
            } else if (checkWordResult == 5) {
                JOptionPane.showMessageDialog(null, WORD_ERROR_MSG);

            }
        });

        // add word list, honeycomb, and user input field to the frame
        frame.add(wordListPanel, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(honeyCombPanel, BorderLayout.WEST);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    /**
     * The main method to start the program.
     * 
     * @param args None required
     */
    public static void main(String[] args) {

        new SpellingBeeGUI();
    }

}
