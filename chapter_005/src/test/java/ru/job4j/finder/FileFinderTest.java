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

    @Before
    public void setUp() throws Exception {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + "/TEST_DIR";
        new File(parent).mkdirs();
        new File(parent + "/test1").mkdirs();
        new File(parent + "/test2").mkdirs();
        new File(parent + "/test1/test.txt").createNewFile();
        new File(parent + "/test2/weather.jpg").createNewFile();
    }

    public void testFinder(String[] args, String expected) throws IOException {
        FileFinder.main(args);
        String readLine;
        try (BufferedReader br = new BufferedReader(new FileReader(
                System.getProperty("java.io.tmpdir") + "/TEST_DIR/log.txt"))) {
            readLine = br.readLine();
        }
        assertThat(readLine, is(expected));
    }

    @Test
    public void whenFileFinderFindFileByNameThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d",
                System.getProperty("java.io.tmpdir") + "/TEST_DIR", "-n", "test.txt", "-f", "-o",
                System.getProperty("java.io.tmpdir") + "/TEST_DIR/log.txt"};
        String expected = System.getProperty("java.io.tmpdir") + "/TEST_DIR" + "/test1/test.txt";
        testFinder(args, expected);
    }

    @Test
    public void whenFileFinderFindFileByMaskThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d",
                System.getProperty("java.io.tmpdir") + "/TEST_DIR", "-n", "*.jpg", "-m", "-o",
                System.getProperty("java.io.tmpdir") + "/TEST_DIR" + "/log.txt"};
        String expected = System.getProperty("java.io.tmpdir") + "/TEST_DIR" + "/test2/weather.jpg";
        testFinder(args, expected);
    }

    @Test
    public void whenFileFinderFindFileByRegExThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d",
                System.getProperty("java.io.tmpdir") + "/TEST_DIR", "-n", "(weather).*", "-r", "-o",
                System.getProperty("java.io.tmpdir") + "/TEST_DIR" + "/log.txt"};
        String expected = System.getProperty("java.io.tmpdir") + "/TEST_DIR" + "/test2/weather.jpg";
        testFinder(args, expected);
    }
/**
    @After
    public void tearDown() {
        new File(System.getProperty("java.io.tmpdir") + "TEST_DIR" + "/test1", "test.txt").delete();
        new File(System.getProperty("java.io.tmpdir") + "TEST_DIR" + "/test2", "weather.jpg").delete();
        new File(System.getProperty("java.io.tmpdir") + "TEST_DIR" + "/log.txt").delete();
        new File(System.getProperty("java.io.tmpdir") + "TEST_DIR" + "/test1").delete();
        new File(System.getProperty("java.io.tmpdir") + "TEST_DIR" + "/test2").delete();
        new File(System.getProperty("java.io.tmpdir") + "TEST_DIR").delete();
    }*/
}