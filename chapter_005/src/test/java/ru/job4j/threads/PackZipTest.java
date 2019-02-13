package ru.job4j.threads;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Test Class PackZipTest.
 *
 * @author shustovakv
 * @since 10.02.2019
 */
public class PackZipTest {
    @Test
    public void whenPackZipCreateZipFile() {
        String[] args = {"java -jar pack.jar", "-d", "C:\\Users\\Kseniya\\AppData\\Local\\Temp\\parent", "-e", "java.xml", "-o", "example.zip"};
        Args argument = new Args(args);
        PackZip packZip = new PackZip();
        packZip.zip(argument);
        assertTrue(new File(argument.directory() + "\\" + argument.output()).exists());
    }
}
