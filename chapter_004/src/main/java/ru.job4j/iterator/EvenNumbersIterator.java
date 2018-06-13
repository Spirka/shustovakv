package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenNumbersIterator.
 *
 * @author shustovakv
 * @since 13.06.2018
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    /**
     * Field List of arbitrary numbers.
     */
    private int[] array;

    /**
     * Field position.
     */
    private int index = 0;

    /**
     * Constructor
     * @param array List of arbitrary numbers.
     */
    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    /**
     * Method hasNext
     * @return true only if the array has even numbers before the pointer.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        while (this.array.length > this.index) {
            if (this.array[index] % 2 == 0) {
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Method next
     * @return only even numbers
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.array[index++];
    }
}
