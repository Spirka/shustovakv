package ru.job4j.parser;

import java.util.Date;
import java.util.Objects;

/**
 * Class Entry.
 *
 * @author shustovakv
 * @since 27.08.2019
 */
public class Entry {

    private static final String LN = System.getProperty("line.separator");

    private int id;
    private String name;
    private String desc;
    private String link;
    private Date posted;

    public Entry(int id, String name, String desc, String link, Date posted) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.posted = posted;
    }

    public Entry(String name, String desc, String link) {
        this.name = name;
        this.desc = desc;
        this.link = link;
    }

    public Entry(String name, String desc, String link, Date posted) {
        this.name = name;
        this.desc = desc;
        this.link = link;
        this.posted = posted;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public Date getPosted() {
        return posted;
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
        return id == entry.id && Objects.equals(name, entry.name) && Objects.equals(desc, entry.desc)
                && Objects.equals(link, entry.link) && Objects.equals(posted, entry.posted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, link, posted);
    }

    @Override
    public String toString() {
        return "Entry{" + LN
                + "name='" + name + '\'' + "," + LN
                + "desc='" + desc + '\'' + "," + LN
                + "link='" + link + '\'' + "," + LN
                + "posted=" + posted
                + '}' + LN;
    }
}
