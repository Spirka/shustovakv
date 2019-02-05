package ru.job4j.threads;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test Class SearchTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 25.01.2019
 */
public class SearchTest {
    @Ignore
    @Test
    public void whenSearchTxtFiles() throws IOException {
        Search search = new Search();
        String tmpdir = System.getProperty("java.io.tmpdir");
        File parent = new File(tmpdir + "parent");
        parent.mkdir();
        File folder1 = new File(parent.getPath() + "/папка1");
        folder1.mkdir();
        File fileTxt = new File(parent, "note.txt");
        File fileTxt1 = new File(parent, "myNotes.txt");
        File fileJpg = new File(parent, "weather.jpg");
        File fileTxt2 = new File(folder1, "myBooks.txt");
        fileTxt.createNewFile();
        fileJpg.createNewFile();
        fileTxt1.createNewFile();
        fileTxt2.createNewFile();
        List<String> ext = Arrays.asList("txt");
        List<File> list = search.files(parent.getPath(), ext);
        List<File> expect = Arrays.asList(fileTxt2, fileTxt, fileTxt1);
        assertThat(list.toString(), is(expect.toString()));
    }
}
