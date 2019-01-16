package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Class checkWord
 * Counts duplicate characters in a word
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 16/01/2019
 */
public class CheckWord {

    public String word;

    public CheckWord(String s) {
        this.word = s;
    }

    /**
     * Counts duplicate characters in a word
     * @param word
     * @return map
     */
    public Map<Character, Integer> count(String word) {
    Map<Character, Integer> map = new HashMap<>();

    for(int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (map.containsKey(c)) {
            int count = map.get(c);
            map.put(c, ++count);
        } else {
            map.put(c, 1);
        }
    }
    return map;
    }
}
