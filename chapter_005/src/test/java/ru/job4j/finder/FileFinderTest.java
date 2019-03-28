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

    private static final Character FN = File.separatorChar;

    @Before
    public void setUp() throws Exception {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + FN + "TEST_DIR";
        new File(parent).mkdirs();
        new File(parent + FN + "test1").mkdirs();
        new File(parent + FN + "test2").mkdirs();
        new File(parent + FN + "test1" + FN + "test.txt").createNewFile();
        new File(parent + FN + "test2" + FN + "weather.jpg").createNewFile();
    }

    public void testFinder(String[] args, String expected) throws IOException {
        FileFinder.main(args);
        String readLine;
        try (BufferedReader br = new BufferedReader(new FileReader(
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "log.txt"))) {
            readLine = br.readLine();
        }
        readLine = readLine.substring(readLine.lastIndexOf(FN) + 1);
        assertThat(readLine, is(expected));
    }

    @Test
    public void whenFileFinderFindFileByNameThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d",
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR", "-n", "test.txt", "-f", "-o",
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "log.txt"};
        String expected = "test.txt";
        testFinder(args, expected);
    }

    @Test
    public void whenFileFinderFindFileByMaskThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d",
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR", "-n", "*.jpg", "-m", "-o",
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "log.txt"};
        String expected = "weather.jpg";
        testFinder(args, expected);
    }

    @Test
    public void whenFileFinderFindFileByRegExThenItRecordedInLog() throws IOException {
        String[] args = {"-jar", "FileFinder.jar", "-d",
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR", "-n", "(weather).*", "-r", "-o",
                System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "log.txt"};
        String expected = "weather.jpg";
        testFinder(args, expected);
    }

    @After
    public void tearDown() {
        new File(System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "test1", "test.txt").delete();
        new File(System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "test2", "weather.jpg").delete();
        new File(System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "log.txt").delete();
        new File(System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "test1").delete();
        new File(System.getProperty("java.io.tmpdir") + FN + "TEST_DIR" + FN + "test2").delete();
        new File(System.getProperty("java.io.tmpdir") + FN + "TEST_DIR").delete();
    }
}