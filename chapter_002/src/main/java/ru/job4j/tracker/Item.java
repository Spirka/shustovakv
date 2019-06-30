package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class Item.
 * @author  shustovakv
 * @since 23.11.2017
 */
public class Item {

    public Item(String name, String desc, Long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }
    /**
     * Поле идентификатор.
     */
    private String id;
    /**
     * Поле имя.
     */
    private String name;
    /**
     * Поле описание.
     */
    private String desc;
    /**
     * Поле дата создания.
     */
    private long created;
    /**
     * Поле комментарий.
     */
    private ArrayList<String> comments;
    /**
     * Метод getId.
     */
    public String getId() {
        return id;
    }
    /**
     * Метод setId.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Метод getName.
     */
    public String getName() {
        return name;
    }
    /**
     * Метод setName.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Метод getDesc.
     */
    public String getDesc() {
        return desc;
    }
    /**
     * Метод setDesc.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * Метод getCreated.
     */
    public long getCreated() {
        return created;
    }
    /**
     * Метод setCreated.
     */
    public void setCreated(long created) {
        this.created = created;
    }
    /**
     * Метод getComments.
     */
    public ArrayList<String> getComments() {
        return comments;
    }
    /**
     * Метод setComments.
     */
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return created == item.created && Objects.equals(id, item.id) && Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc) && Objects.equals(comments, item.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, desc, created, comments);
    }
}
