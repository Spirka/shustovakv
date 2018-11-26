package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class HashMap.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 27.09.2018
 */
public class HashMap<K, V> implements Iterable {

    private Items[] array;
    private int defaultSize = 10;
    private int size = 0;
    private int modCount = 0;

    public HashMap(int capacity) {
        if (capacity < this.defaultSize) {
            capacity = this.defaultSize;
        }
        this.array = new Items[capacity];
    }

    public HashMap() {
        this.array = new Items[this.defaultSize];
    }

    public boolean insert(K key, V value) {
        int factor = this.array.length;
        if (this.size > factor * 2 / 3) {
            resize(0);
            factor = this.array.length;
        }
            int index = findPosition(key, factor);
        if (this.array[index] != null) {
            return false;
        }
        this.array[index] = new Items(key, value);
        this.modCount++;
        this.size++;
        return true;
    }

    private void resize(int moreSize) {
        Items[] newArray = new Items[this.array.length * 2 + moreSize];
        int factor = newArray.length;
        for (int i = 0; i < array.length; i++) {
            if (this.array[i] != null) {
                int index = findPosition((K) array[i].key, factor);
                if (newArray[i] == null) {
                    newArray[index] = this.array[i];
                } else {
                    restart(factor);
                    break;
                }
            }
        }
        this.array = newArray;
    }

    private void restart(int factor) {
        resize(factor);
    }

    private int findPosition(K key, int factor) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        h ^= (h >>> 2) ^ (h >>> 3);
        h %= factor;
        return h >= 0 ? h : h * -1;
    }

    public V get(K key) {
        int index = findPosition(key, this.array.length);
        if (this.array[index] != null && this.array[index].key.equals(key)) {
            return (V) this.array[index].value;
        }
        return null;
    }

    public boolean delete(K key) {
        this.modCount--;
        int index = findPosition(key, this.array.length);
        if (this.array[index] != null && this.array[index].key.equals(key)) {
            this.array[index] = null;
            this.size--;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int itIndex = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Only for read");
                }
                while (itIndex < getCapacity()) {
                    if (array[itIndex] != null) {
                        return true;
                    } else {
                        itIndex++;
                    }
                }
                return false;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                return (V) array[itIndex++].value;
            }
        };
    }

    public void showHashMap() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.println("Key: " + array[i].key + "Value: " + array[i].value);
            }
        }
    }

    public int getCapacity() {
        return this.array.length;
    }

    class Items<K, V> {

        private K key;
        private V value;

        public Items(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Items<?, ?> items = (Items<?, ?>) o;
            return Objects.equals(key, items.key) && Objects.equals(value, items.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
