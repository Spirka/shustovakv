package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Bank.
 *
 * @author shustovakv
 * @since 08.06.2018
 */
public class MatrixIterator implements Iterator<Integer> {

    /**
     * Field Two dimensional array
     */
    private final int[][] values;
    private int posX = 0;
    private int posY = 0;

    /**
     * Constructor
     * @param values the Two dimensional array
     */
    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    /**
     * Method hasNext
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return (this.values.length > 0 && (this.posY < this.values.length - 1 || this.posX < values[this.posY].length));
    }

     /**
     * Method next
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.posX == values[this.posY].length) {
            this.posX = 0;
            this.posY++;
        }
        return values[this.posY][this.posX++];
    }
}
