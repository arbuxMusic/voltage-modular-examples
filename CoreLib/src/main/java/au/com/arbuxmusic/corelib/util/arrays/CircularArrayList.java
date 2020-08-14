package au.com.arbuxmusic.corelib.util.arrays;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * A circular buffer of specified size for strings.
 * Useful for logs
 */
public class CircularArrayList<E> {

    /**
     * The array of items
      */
    private ArrayList<E> arrayList;

    /**
     * The default value for reset/new items
     */
    private E defaultValue;

    /**
     * the index of the most recent value added to the array
     */
    private AtomicInteger currentIndex = new AtomicInteger(0);

    /**
     * The functor to enable looping for the array
     */
    private final IntBinaryOperator addOrWrap = (x, y) -> (x + y >= arrayList.size() ? 0 : x + y);

    /**
     * Returh the size of the array
     * @return the size of the array
     */
    public int size() {
        return arrayList.size();
    }

    /**
     * Constructor for a circular array list
     * @param size the size of the array
     */
    public CircularArrayList(int size, E defaultValue) throws IllegalArgumentException {
        // The array must have at least 1 element
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }

        // Setup the new array
        arrayList = new ArrayList<E>(size);
        this.defaultValue = defaultValue;

        // populate the entries into the array list
        for (int i = 0; i < size; i++) {
            arrayList.add(defaultValue);
        }

        // Set the initial position of the current index in the array
        currentIndex.set(arrayList.size() - 1);
    }

    /**
     * Add the item to the array
     * @param item the item to be added
     */
    public synchronized void put(E item) {
        int index = currentIndex.accumulateAndGet(1, addOrWrap);
        arrayList.set(index, item);
    }

    /**
     * Populate the target array with the circular array list items
     * @param targetArray The target array to be populated with elements. Can be smaller than the source array
     */
    public synchronized void toArray(E[] targetArray) {
        int readPoint = currentIndex.get();

        for (int targetIndex = targetArray.length - 1; targetIndex >=0; targetIndex-- ) {
            targetArray[targetIndex] = arrayList.get(readPoint--);
            if (readPoint < 0)
                readPoint = arrayList.size() - 1;
        }
    }

    /**
     * Reset the array to the default value for all entries
     */
    public synchronized void reset() {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, defaultValue);
        }
        currentIndex.set(arrayList.size() - 1);
    }
}
