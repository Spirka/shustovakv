package ru.job4j.threads;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class InputStreamFilter.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.01.2019
 */

public class InputStreamFilterTest {

    @Test
    public void dropAbuses() throws IOException {
        String phrase = "Сижу дома не работаю, не учусь.";
        String[] abuse = {"не "};
        String result = "Сижу дома работаю, учусь.";

        try (InputStream in = new ByteArrayInputStream(phrase.getBytes())) {
            OutputStream out = new ByteArrayOutputStream();
            InputStreamFilter inputStreamFilter = new InputStreamFilter();
            inputStreamFilter.dropAbuses(in, out, abuse);
            assertThat(result.equals(out.toString()), is(true));
        }
    }
}