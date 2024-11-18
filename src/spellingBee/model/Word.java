package spellingBee.model;

import java.util.List;

/**
 * Word Class to check required rules for correct words
 */
public class Word {
    private String word;
    private boolean isPangram;

    public Word(String input) {
        word = input;
    }

    /**
     * Check if the word length is 4 and more
     * 
     * @return true if word is longer than 4 letters and false otherwise
     */
    public boolean checkLength() {
        if (this.word.length() < 4) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the word includes the center letter
     * 
     * @param center the center letter
     * @return true if word has the center letter false otherwise
     */
    public boolean checkCenter(char center) {
        boolean centerIncluded = false;
        char[] wordArr = this.word.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i] == center) {
                centerIncluded = true;
            }
        }
        return centerIncluded;
    }

    /**
     * Check if the word is made from letters of the game set up
     * 
     * @param letters      outer letters
     * @param centerLetter the center letter
     * @return true if word is made up of set up letters & center letter and
     *         false otherwise
     */
    public boolean checkLetters(char[] letters, char centerLetter) {
        boolean letterUsed = false;
        char[] wordArr = this.word.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            for (int j = 0; j < letters.length; j++) {
                if (wordArr[i] == centerLetter) {
                    letterUsed = true;
                    break;
                } else if (wordArr[i] == letters[j]) {
                    letterUsed = true;
                }
            }
            if (!letterUsed) {
                return false;
            } else {
                letterUsed = false;
            }
        }
        return true;
    }

    /**
     * checks if the word is in the dictionary/valid
     * 
     * @param validWords a list of words from common words text file and english
     *                   words text file
     * @return true if word is valid false otherwise
     */
    public boolean isValid(List<String> validWords) {
        if (validWords.contains(this.word)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set pangram status for a word that is made with all 7 letters
     * 
     * @param letters the selected 7 letters to be used in the game
     * 
     */
    public void setPangram(char[] letters) {
        boolean letterUsed = false;
        char[] wordArr = this.word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < wordArr.length; j++) {
                if (letters[i] == wordArr[j]) {
                    letterUsed = true;
                }
            }
            if (letterUsed == false) {
                this.isPangram = false;
                return;
            } else {
                letterUsed = false;
            }
        }
        this.isPangram = true;
    }

    public String getWord() {
        return this.word;
    }

    public boolean getPangram() {
        return this.isPangram;
    }

}
