package ru.job4j.magnit;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class MagnitStoreTest {

    private static final Character FILE_SEPARATOR = File.separatorChar;

    private Entries revert(File file) {
        Entries entries = new Entries();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Entries) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * check StoreSQL
     */
    @Test
    public void whenGenerateTableInDataBaseThenGetListOfEntries() throws Exception {
        Config config = new Config();
        List<Entry> expected = new ArrayList<>();
        expected.add(new Entry(1));
        expected.add(new Entry(2));
        List<Entry> entries;
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.generate(2);
            entries = storeSQL.load();
        }
        assertThat(entries, is(expected));
    }

    @Test
    public void whenCreateXMLFileFromEntryDataBase() throws IOException, SQLException {
        Config config = new Config();
        String path = System.getProperty("java.io.tmpdir");
        File context = new File(path + FILE_SEPARATOR + "test.xml");
        assertTrue(context.createNewFile());
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(2);
        StoreXML storeXML = new StoreXML(context);
        storeXML.save(storeSQL.load());
        Entries expected = new Entries(Arrays.asList(new Entry(1), new Entry(2)));
        assertThat(this.revert(context), is(expected));
    }

    @Test
    public void whenXMLFileConvertTOAnotherXMLFileThenParseItAndReturnSum() throws IOException, SAXException, ParserConfigurationException {
        String path = System.getProperty("java.io.tmpdir");
        File dest = new File(path + FILE_SEPARATOR + "converted.xml");
        File scheme = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("scheme.xsl")).getFile());
        File context = new File(path + FILE_SEPARATOR + "test.xml");
        ConvertXSQT convert = new ConvertXSQT();
        convert.convert(context, dest, scheme);
        SAXCounter saxCounter = new SAXCounter(dest);
        assertThat(saxCounter.sumFieldFromXML(), is(3));
    }

    @Test
    public void whenApplicationParsesInputFileThenItReturnSumOfEntries() throws IOException, SAXException, ParserConfigurationException, SQLException {
        String path = System.getProperty("java.io.tmpdir");
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.generate(1000000);
        File context = new File(path + FILE_SEPARATOR + "test2.xml");
        StoreXML storeXML = new StoreXML(context);
        storeXML.save(storeSQL.load());
        File dest = new File(path + FILE_SEPARATOR + "converted2.xml");
        File scheme = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("scheme.xsl")).getFile());
        ConvertXSQT convert = new ConvertXSQT();
        convert.convert(context, dest, scheme);
        SAXCounter saxCounter = new SAXCounter(dest);
        assertThat(saxCounter.sumFieldFromXML(), is(1784293664));
    }
}