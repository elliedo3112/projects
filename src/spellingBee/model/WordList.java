package spellingBee.model;

import java.util.ArrayList;
import java.util.List;

import spellingBee.view.WordListListener;

/**
 * WordList class to add new correct words in the appropriate position within
 * the word list
 */
public class WordList {
    private List<WordListListener> listeners = new ArrayList<>();
    private List<Word> wordList = new ArrayList<>();

    /**
     * add the new word to the word list
     * 
     * @param newWord new word object to be added to the display list
     */
    public void add(Word newWord) {
        int index = binarySearch(wordList, newWord);
        if (index < 0) {
            index = -(index + 1); // Convert to insertion point
        }
        wordList.add(index, newWord);
        notifyListeners();

    }

    /**
     * Find the index to add the next word object to the word list in
     * alphabetical order using binary search
     * 
     * @param list list of word objects that is in the correct words list
     * @param word new word object
     * @return the index of the word object in the list
     */
    public int binarySearch(List<Word> list, Word word) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = list.get(mid).getWord().compareTo(word.getWord());
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -(low + 1);
    }

    /**
     * check if this word is already in the word list
     * 
     * @param word word object to be checked
     * @return true if word is not in the list and false otherwise
     */
    public boolean notExist(Word word) {
        boolean existing = true;
        for (int i = 0; i < wordList.size(); i++) {
            if (word.getWord().equals(wordList.get(i).getWord())) {
                existing = false;
            }
        }
        return existing;
    }

    private void notifyListeners() {
        for (WordListListener listener : listeners) {
            listener.update();
        }
    }

    public void addListener(WordListListener listener) {
        listeners.add(listener);
    }

    public List<Word> getWordList() {
        return this.wordList;
    }
}
