package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Class MaxMin
 *
 * @author Kseniya Dergunova
 * @since 11.04.2020
 */
public class MaxMin {

    /**
     * Метод, в котором происходят вычислания наименьшего и наибольшего элемента
     * @param value список элементов.
     * @param comparator объект компаратор
     * @param <T> Тип данных
     * @return максимальный или минимальный элемент
     */
    private  <T> T maxMin(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T element : value) {
            if (comparator.compare(element, result) > 0) {
                result = element;
            }
        }
        return result;
    }

    /**
     * Метод для поиска самого большого элемента в списке.
     * @param value список элементов.
     * @param comparator объект компаратор
     * @param <T> Тип данных
     * @return максимальный элемент
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator);
    }

    /**
     * Метод для поиска наименьшего элемента в списке.
     * @param value список элементов.
     * @param comparator объект компаратор
     * @param <T> Тип данных
     * @return минимальный элемент
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
       return maxMin(value, (o1, o2) -> comparator.compare(o2, o1));
    }
}
