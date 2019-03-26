package ru.job4j.finder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileFinderTest {

    private static final String TEST_DIR = System.getProperty("java.io.tmpdir") + "TEST_DIR";

    @Before
    public void setUp() throws Exception {
        new File(TEST_DIR).mkdirs();
        new File(TEST_DIR + "/test1").mkdirs();
        new File(TEST_DIR + "/test2").mkdirs();
        File file = new File(TEST_DIR + "/test1/test.txt");
        File file1 = new File(TEST_DIR + "/test2/weather.jpg");
        file.createNewFile();
        file1.createNewFile();
    }

    public void testFinder(String[] args, String expected) throws IOException {
        FileFinder.main(args);
        String readLine;
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_DIR + "\\log.txt"))) {
            readLine = br.readLine();
        }
        assertThat(readLine, is(expected));
    }

    @Test
    public void whenFileFinderFindFileByNameThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d", TEST_DIR, "-n", "test.txt", "-f", "-o", TEST_DIR + "\\log.txt"};
        String expected = TEST_DIR + "\\test1\\test.txt";
        testFinder(args, expected);
    }

    @Test
    public void whenFileFinderFindFileByMaskThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d", TEST_DIR, "-n", "*.jpg", "-m", "-o", TEST_DIR + "\\log.txt"};
        String expected = TEST_DIR + "\\test2\\weather.jpg";
        testFinder(args, expected);
    }

    @Test
    public void whenFileFinderFindFileByRegExThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d", TEST_DIR, "-n", "(weather).*", "-r", "-o", TEST_DIR + "\\log.txt"};
        String expected = TEST_DIR + "\\test2\\weather.jpg";
        testFinder(args, expected);
    }

    @After
    public void tearDown() {
        new File(TEST_DIR + "/test", "test.txt").delete();
        new File(TEST_DIR + "/test2", "weather").delete();
        new File(TEST_DIR + "/log.txt").delete();
        new File(TEST_DIR + "/test1").delete();
        new File(TEST_DIR + "/test2").delete();
        new File(System.getProperty("java.io.tmpdir") + "/TEST_DIR").delete();
    }
}