package ru.job4j.array;
/**
 * Class Turn.
 * @author  shustovakv
 * @since 04.11.2017
 */
public class Turn {
    /**
     * back.
     *
     * @param array массив целочисленных значений.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        if (array == null || array.length % 2 != 0) {
            for (int i = 0; i <= array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        } else {
            for (int i = 0; i < array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        }
        return array;
    }
}