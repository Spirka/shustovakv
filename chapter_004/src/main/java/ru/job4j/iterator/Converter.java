package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class Converter.
 * @author  shustovakv
 * @since 08.07.2018
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> currentNext = it.next();

            @Override
            public boolean hasNext() {
                if (!currentNext.hasNext() && it.hasNext()) {
                    currentNext = it.next();
                }
                return currentNext.hasNext();
            }

            @Override
            public Integer next() {
                if (!currentNext.hasNext()) {
                    currentNext = it.next();
                }
                return currentNext.next();
            }
        };
    }
}
