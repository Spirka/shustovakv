package ru.job4j.threads;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test Class PackZipTest.
 *
 * @author shustovakv
 * @since 10.02.2019
 */
public class PackZipTest {

    @Test
    public void whenArgsIsValid() {
        String[] args = {"java -jar pack.jar", "-d", "C:\\Users\\Kseniya\\AppData\\Local\\Temp\\parent", "-e", "java.xml", "-o", "example.zip"};
        Args argument = new Args(args);
        assertThat(argument.directory(), is("C:\\Users\\Kseniya\\AppData\\Local\\Temp\\parent"));
        assertThat(argument.output(), is("example.zip"));
        assertThat(argument.exclude(), is(Arrays.asList("java.xml")));
    }

    @Test
    public void whenPackZipCreateTestZipFile() throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        String parent = path + "/parent";
        new File(parent).mkdirs();
        new File(parent + "/child1").mkdirs();
        new File(parent + "/child2").mkdirs();
        new File(parent + "/child1/child3").mkdirs();
        File file = new File(parent + "/child1/child3/test.txt");
        file.createNewFile();
        new File(parent + "/child1/child3/test.txt").createNewFile();
        String[] args = {"java -jar pack.jar", "-d", parent, "-e", "java.xml", "-o", "myFolder.zip"};
        Args argument = new Args(args);
        PackZip packZip = new PackZip();
        packZip.zip(argument);
        assertTrue(new File(argument.directory() + "\\" + argument.output()).exists());
    }
}
