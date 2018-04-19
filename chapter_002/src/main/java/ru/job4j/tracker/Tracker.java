package ru.job4j.tracker;

import java.util.*;

/**
 * Class Tracker.
 * @author  shustovakv
 * @since 23.11.2017
 */
public class Tracker {
    /**
     * Поле массив объектов класса Item.
     */
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * Поле генерации случайных чисел.
     */
    private static final Random RN = new Random();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }
    /**
     * Метод генерирующий уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    /**
     * Метод обновляющий значение заявки. Должен заменить ячейку в массиве this.items
     * Для этого необходимо найти ячейку в массиве по id (id у Item можно получить с помощью метода getId)
     * Обязательно принимать один параметр Item, а не список полей.
     * @param item новая заявка.
     */
    public void update(Item item) {
        for (Item element : items) {
            if (element.getId().equals(item.getId())) {
                this.items.set(this.items.indexOf(element), item);
                break;
            }
        }
    }
    /**
     * Метод public void delete(Item) должен удалить ячейку в массиве this.items.
     * Для этого необходимо найти ячейку в массиве по id.
     * После этого присвоить ей null, либо сместить все значения справа от удаляемого элемента - на
     * одну ячейку влево с помощью System.arrayCopy().
     * @param item заявка.
     */
    public void delete(Item item) {
        for (Item element : items) {
            if (element.getId().equals(item.getId())) {
                this.items.remove(element);
                break;
            }
        }
    }
    /**
     * Метод public Item[] findAll() реализует получение списка заявок.
     * @return копию массива this.items без null элементов.
     */
    public ArrayList<Item> findAll() {
        return new ArrayList<>(this.items);
    }
    /**
     * Метод получение списка по имени. проверяет в цикле все элементы массива this.items,
     * сравнивая name (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его.
     * @param key уникальный ключ.
     * @return результирующий массив.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item element : this.items) {
            if (element.getName().contains(key)) {
                result.add(element);
            }
        }
        return result;
    }
    /**
     * Проверяет в цикле все элементы массива this.items, сравнивая id с аргументом String id.
     * @param id идентификатор.
     * @return найденный Item. Если Item не найден - возвращает null.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item element : items) {
            if (element.getId().equals(id)) {
                result = this.items.get(this.items.indexOf(element));
                break;
            }
        }
        return result;
    }
}
