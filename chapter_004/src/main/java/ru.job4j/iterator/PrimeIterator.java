package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator<Integer> {

    private final int[] numbers;
    private int index = 0;

    public PrimeIterator(final int[] numbers) {
        this.numbers = numbers;
    }


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

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.numbers[index++];
    }

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