package ru.job4j.magnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Class Entry.
 *
 * @author shustovakv
 * @since 30.06.2019
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {

    @XmlElement
    private Integer value;

    public Entry() {
    }

    public Entry(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return value.equals(entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
