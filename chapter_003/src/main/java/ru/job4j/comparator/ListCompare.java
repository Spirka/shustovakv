package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Class ListCompare.
 * @author  shustovakv
 * @since 25.04.2018
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {

        int result = 0;

        char[] charsLeft = left.toCharArray();
        char[] charsRight = right.toCharArray();
        int lengthChars = Math.min(charsLeft.length, charsRight.length);

        for (int i = 0; result == 0 && i < lengthChars; i++) {
            result = Character.compare(charsLeft[i], charsRight[i]);
        }
        if (result == 0) {
            result = Integer.compare(charsLeft.length, charsRight.length);
        }
        return result;
    }
}
