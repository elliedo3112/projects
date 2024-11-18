package spellingBee.model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * SpellingBeeGame Class to set up, control game, and check input words
 */
public class SpellingBeeGame {
    private gameSetUp setUp = new gameSetUp();
    private char[] letters = new char[7];
    private char centerLetter;
    private WordList list = new WordList();
    private WordCount count = new WordCount();
    private char[] fullArray = new char[7];

    private void setUp() {
        setUp.dictSetUp();

    }

    private char[] selectLetters(List<String> dictionary) {
        Random random = new Random();
        int randomNumber = random.nextInt(dictionary.size());
        String mainWord = dictionary.get(randomNumber);

        Set<Character> uniqueLetters = new HashSet<>();
        for (int i = 0; i < mainWord.length(); i++) {
            char letter = mainWord.charAt(i);
            uniqueLetters.add(letter);
        }
        Character[] array = uniqueLetters
                .toArray(new Character[uniqueLetters.size()]);
        for (int i = 0; i < 7; i++) {
            fullArray[i] = array[i].charValue();
        }

        return fullArray;

    }

    public void startGame() {
        setUp();
        fullArray = selectLetters(setUp.getDict());
        Random random = new Random();
        int randomNumber = random.nextInt(7);

        setCenter(fullArray[randomNumber]);
        setLetters(fullArray);

    }

    /**
     * check input word
     * 
     * @param input  user input word
     * @param array  outer letters
     * @param center center letter
     * @return return a number indicating actions (add word or error message) 1:
     *         not long enough 2: no center letter 3: letters not in honeycomb
     *         4: invalid word 5: existing word
     */
    public int checkWord(String input, char[] array, char center) {
        Word word = new Word(input);
        if (!word.checkLength()) {
            return 1;
        } else if (!word.checkCenter(center)) {
            return 2;
        } else if (!word.checkLetters(array, center)) {
            return 3;
        } else if (!word.isValid(setUp.getCorrectWords())) {
            return 4;
        } else if (list.notExist(word) == false) {
            return 5;
        } else {
            word.setPangram(array);
            this.list.add(word);

            this.count.setCount();
            return 0;
        }

    }

    /**
     * set outer letters
     * 
     * @param array to set outer letters in honeycomb
     */
    public void setLetters(char[] array) {

        char[] newArray = new char[6];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (array[i] != centerLetter) {
                newArray[j++] = array[i];
            }
        }
        this.letters = newArray;
    }

    private void setCenter(char centerLetter) {
        this.centerLetter = centerLetter;
    }

    public char[] getLetters() {
        return letters;
    }

    public char getCenter() {
        return centerLetter;
    }

    public WordList getWordList() {
        return list;
    }

    public WordCount getCount() {
        return count;
    }

    public gameSetUp getSetUp() {
        return this.setUp;
    }

}
