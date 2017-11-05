package ru.job4j.array;
/**
 * Class BubbleSort.
 * @author  shustovakv
 * @since 05.11.2017
 */
public class BubbleSort {
    /**
     * sort.
     *
     * @param array массив целочисленных значений.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        for (int i = array.length - 1;i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
        return array;
    }
}
