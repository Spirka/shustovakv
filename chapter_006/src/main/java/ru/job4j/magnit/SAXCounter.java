package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Class SAXCounter.
 *
 * @author shustovakv
 * @since 04.07.2019
 */
public class SAXCounter {

    private File file;

    public SAXCounter(File file) {
        this.file = file;
    }

    public Integer sumFieldFromXML() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(file, handler);
        return XMLHandler.getSum();
    }

    private static class XMLHandler extends DefaultHandler {

        private static Integer sum = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if ("entry".equals(qName)) {
                sum += Integer.parseInt(attributes.getValue("field"));
            }
        }

        public static Integer getSum() {
            return sum;
        }
    }
}
