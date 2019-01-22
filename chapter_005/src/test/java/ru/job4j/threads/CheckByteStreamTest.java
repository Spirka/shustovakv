package ru.job4j.threads;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Test Class CheckByteStreamTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 22.01.2019
 */
public class CheckByteStreamTest {

    CheckByteStream check = new CheckByteStream();
    String input;

    @Test
    public void whenNumberIsEven() {
        input = "16";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        assertTrue(check.isNumber(in));
    }

    @Test
    public void whenNumberIsNotEven() {
        input = "13";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        assertFalse(check.isNumber(in));
    }
}