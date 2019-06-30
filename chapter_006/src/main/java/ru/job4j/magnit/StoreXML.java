package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Class StoreXML.
 *
 * @author shustovakv
 * @since 30.06.2019
 */
public class StoreXML {

    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        try {
            Entries entries = new Entries(list);
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, this.target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
