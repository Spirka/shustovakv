package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Class Entries.
 *
 * @author shustovakv
 * @since 30.06.2019
 */

@XmlRootElement
public class Entries {

    public Entries() {
    }

    private List<Entry> entry;

    public Entries(List<Entry> list) {
        this.entry = list;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entries entries = (Entries) o;
        return Objects.equals(entry, entries.entry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry);
    }
}
