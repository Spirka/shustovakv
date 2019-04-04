package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Class ConfigTest.
 *
 * @author shustovakv
 * @since 03.04.2019
 */
public class ConfigTest {

    private static final String LN = System.getProperty("line.separator");
    private static final Character FILE_SEPARATOR = File.separatorChar;

    @Test
    public void whenConfigLoadContext() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        File context = new File(path + FILE_SEPARATOR + "test.txt");
        context.createNewFile();
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(context.getAbsolutePath())))) {
            pw.print("email=test@mail.ru");
        }
        Config config = new Config(path + FILE_SEPARATOR + "test.txt");
        config.load();
        assertThat(config.value("email"), is("test@mail.ru"));
        context.deleteOnExit();
    }

    @Test
    public void whenContextContainsComments() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        File context = new File(path + FILE_SEPARATOR + "test.txt");
        context.createNewFile();
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(context.getAbsolutePath())))) {
            pw.print("//" + "this is a working email" + LN);
            pw.print("email=test@mail.ru");
        }
        Config config = new Config(path + FILE_SEPARATOR + "test.txt");
        config.load();
        assertThat(config.value("email"), is("test@mail.ru"));
        context.deleteOnExit();
    }

    @Test
    public void whenContextContainsMoreThanOneBlankLine() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        File context = new File(path + FILE_SEPARATOR + "test.txt");
        context.createNewFile();
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(context.getAbsolutePath())))) {
            pw.print(LN);
            pw.print("email=test@mail.ru");
            pw.print(LN);
            pw.print(LN);
            pw.print("email_2=shustovakv@mail.ru");
        }
        Config config = new Config(path + FILE_SEPARATOR + "test.txt");
        config.load();
        assertThat(config.value("email"), is("test@mail.ru"));
        assertThat(config.value("email_2"), is("shustovakv@mail.ru"));
        context.deleteOnExit();
    }
}