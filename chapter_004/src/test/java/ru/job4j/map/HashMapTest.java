package ru.job4j.map;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test class HashMapTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 28.09.2018
 */
public class HashMapTest {

    private HashMap<String, String> map = new HashMap<>(10);

    @Test(expected = NoSuchElementException.class)
    public void whenItemPutToMapThenMapHasOneElement() {
        this.map.insert("1", "first");
        assertThat(this.map.get("1"), is("first"));
        Iterator it = this.map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenItemWithTheSameKeyPutToMapThenMapAddedOnlyOneElement() {
        this.map.insert("1", "first");
        assertThat(this.map.get("1"), is("first"));
        Iterator it = this.map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(this.map.insert("1", "first"), is(false));
        assertThat(this.map.insert("2", "second"), is(true));
        it.next();
    }
    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorProtectedDelete() {
        assertThat(this.map.insert("1", "first"), is(true));
        assertThat(this.map.get("1"), is("first"));
        assertThat(this.map.insert("2", "second"), is(true));
        assertThat(this.map.get("2"), is("second"));
        Iterator it = this.map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(this.map.delete("1"), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        it.next();
    }
}
