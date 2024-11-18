package spellingBee.model;

import java.util.ArrayList;
import java.util.List;

import spellingBee.view.CountListener;

/**
 * WordCount class to keep track and update word count
 */
public class WordCount {
    private int wordListSize = 0;
    private List<CountListener> listeners = new ArrayList<>();

    public void setCount() {
        this.wordListSize += 1;
        notifyListeners();

    }

    public int getCount() {
        return this.wordListSize;
    }

    private void notifyListeners() {
        for (CountListener listener : listeners) {
            listener.update();
        }

    }

    public void addListener(CountListener listener) {
        listeners.add(listener);
    }
}
