package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void produceTest() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Kseniya Dergunova");
        args.put("subject", "you");
        String result = new SomeGenerator().produce(template, args);
        String expected = "I am a Kseniya Dergunova, Who are you? ";
        assertEquals(result, expected);
    }

    @Test(expected = AssertionError.class)
    public void whenTemplateHasKeysThatAreNotInMap() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("lastName", "Dergunova");
        args.put("subject", "you");
        String result = new SomeGenerator().produce(template, args);
        String expected = "I am a Dergunova, Who are you? ";
        assertThat(result, is(expected));
    }

    @Test(expected = AssertionError.class)
    public void whenThereAreMoreKeysInMapThenInTemplate() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Kseniya");
        args.put("lastName", "Dergunova");
        args.put("subject", "you");
        String result = new SomeGenerator().produce(template, args);
        String expected = "I am a Kseniya Dergunova, Who are you? ";
        assertThat(result, is(expected));
    }
}
