package ru.job4j.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Class ConvertList.
 * @author  shustovakv
 * @since 26.03.2018
 */
public class ConvertList {
    /**
     * Method toList
     * @param array двумерный массив
     * @return list коллекцию
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] valueArray : array) {
            for (int value : valueArray) {
                list.add(value);
            }
        }
        return list;
    }

    /**
     * Method toArray равномерно разбивает лист на количество строк двумерного массива.
     * @param list коллекция
     * @param rows количество строк двумерного массива
     * @return двумерный массив
     */
    public int[][] toArray(List<Integer> list, int rows) {

        int columns = (list.size() / rows) + (list.size() % rows == 0 ? 0 : 1);
        int[][] result = new int[rows][columns];

        Iterator<Integer> itr = list.iterator();
        for (int[] value : result) {
            for (int i = 0; i < columns; i++) {
                if (itr.hasNext()) {
                    value[i] = itr.next();
                } else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method convert конвертирует лист массивов в один лист Integer.
     * @param list of arrays.
     * @return List Integer.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] element : list) {
            for (int value : element) {
                    result.add(value);
            }
        }
        return result;
    }
}
