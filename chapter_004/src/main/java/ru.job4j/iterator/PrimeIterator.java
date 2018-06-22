package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenNumbersIterator.
 *
 * @author shustovakv
 * @since 18.06.2018
 */

public class PrimeIterator implements Iterator<Integer> {

    /**
     * Field List of arbitrary numbers.
     */
    private final int[] numbers;

    /**
     * Field position.
     */
    private int index = 0;

    /**
     * Constructor
     * @param numbers List of arbitrary numbers.
     */
    public PrimeIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Method hasNext
     * @return true only if the array has prime numbers before the pointer.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = this.index; i < this.numbers.length; i++) {
            if (isPrime(this.numbers[i])) {
                result = true;
                this.index = i;
                break;
            }
        }
        return result;
    }

    /**
     * Method next
     * @return only prime numbers
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.numbers[index++];
    }

    /**
     * Method IsPrime
     * @param number arbitrary number
     * @return true only if the number is prime
     */
    private boolean isPrime(int number) {
        boolean result = true;
        if (number > 1) {
            if (number > 2) {
                if (number % 2 == 0) {
                    result = false;
                } else {
                    for (int i = 2; i < number / 2; i++) {
                        if (number / i == 0) {
                            result = false;
                        }
                    }
                }
            }
        } else {
            result = false;
        }
        return result;
    }
}