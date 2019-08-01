package ru.job4j.magnit;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Class Main.
 *
 * @author shustovakv
 * @since 03.07.2019
 */
public class Main {

    int size;

    public Main(int size) {
        this.size = size;
    }
    /**
     * Start program
     */
    public void init() throws SQLException, IOException, SAXException, ParserConfigurationException {
        File target = new File("test.xml");
        File dest = new File("converted.xml");
        File scheme = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("scheme.xsl")).getFile());
        //Create database
        Config config = new Config();
        StoreSQL storeSQL = new StoreSQL(config);
        //Create table and generate n entries
        storeSQL.generate(this.size);
        //Load list of entries
        List<Entry> list = storeSQL.load();
        StoreXML storeXML = new StoreXML(target);
        //save list of entries from storeXML to another .xml using JAXB
        storeXML.save(list);
        //second transformation to .xml using XSLT
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(target, dest, scheme);
        //parsing
        SAXCounter saxCounter = new SAXCounter(dest);
        System.out.println(saxCounter.sumFieldFromXML());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParserConfigurationException, SAXException, IOException {
        new Main(1000000).init();
    }
}