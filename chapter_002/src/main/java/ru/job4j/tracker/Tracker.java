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
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    /**
     * Поле генерации случайных чисел.
     */
    private static final Random RN = new Random();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        //передаем в заявку рандомный id
        item.setId(this.generateId());
        //добавляем заявку в массив items и передвигаемся в следующую позицию.
        this.items[this.position++] = item;
        // возвращаем заявку.
        return item;
    }
    /**
     * Метод генерирующий уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        // Получаем псевдорандомное число типа int, и чтобы оно не повторялось добавляем текущее время,
        // метод valueOf преобразует числовое значение в строку.
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    /**
     * Метод обновляющий значение заявки. Должен заменить ячейку в массиве this.items
     * Для этого необходимо найти ячейку в массиве по id (id у Item можно получить с помощью метода getId)
     * Обязательно принимать один параметр Item, а не список полей.
     * @param item новая заявка.
     */
    public void update(Item item) {
        for (int index = 0; index < this.position; index++) {
            // Проверяем, если в массиве есть элементы и мы находим id совпадающий с нашим.
            // присваиваем найденному элемену item.
            if (item != null && this.items[index].getId().equals(item.getId())) {
                this.items[index] = item;
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
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(item.getId())) {
                System.arraycopy(this.items, index + 1, this.items, index, this.position - index);
                position--;
                break;
            }
        }
    }
    /**
     * Метод public Item[] findAll() реализует получение списка заявок.
     * @return копию массива this.items без null элементов.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        System.arraycopy(this.items, 0, result, 0, this.position);
        return result;
    }
    /**
     * Метод получение списка по имени. проверяет в цикле все элементы массива this.items,
     * сравнивая name (используя метод getName класса Item) с аргументом метода String key.
     * Элементы, у которых совпадает name, копирует в результирующий массив и возвращает его.
     * @param key уникальный ключ.
     * @return результирующий массив.
     */
    public Item[] findByName(String key) {
        // Создаем результирующий массив.
        Item[] result = new Item[this.position];
        int j = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null && this.items[i].getName().equals(key)) {
                result[j++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, j);
    }
    /**
     * Проверяет в цикле все элементы массива this.items, сравнивая id с аргументом String id.
     * @param id идентификатор.
     * @return найденный Item. Если Item не найден - возвращает null.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
