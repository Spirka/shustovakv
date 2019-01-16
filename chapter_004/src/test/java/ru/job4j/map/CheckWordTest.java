package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class checkWordTest
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 16/01/2019
 */
public class CheckWordTest {

    CheckWord checkWord;
    String word = "maaam";

    @Before
    public void setUp() {

        checkWord = new CheckWord(word);
    }

    @Test
    public void whenWordHas3SymbolsA() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('m', 2);
        map.put('a', 3);
        assertThat(this.checkWord.count(word), is(map));
    }
}