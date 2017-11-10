package ru.job4j.array;
/**
 * Class BubbleSort.
 * @author  shustovakv
 * @since 05.11.2017
 */
public class RotateArray {
    /**
     * rotate.
     *
     * @param array двумерный массив целочисленных значений.
     * @return перевернутый на 90 градусов массив.
     */
    public int[][] rotate(int[][] array) {
        if (array.length % 2 == 0) { // условие четности
            for (int i = 0; i < array.length / 2; i++) {
                for (int j = 0; j < array.length / 2; j++) {
                    int temp = array[i][j];
                    array[i][j] = array[array.length - 1 - j][i];
                    array[array.length - 1 - j][i] = array[array.length - 1 - i][array.length - 1 - j];
                    array[array.length - 1 - i][array.length - 1 - j] = array[j][array.length - 1 - i];
                    array[j][array.length - 1 - i] = temp;
                }
            }
            return array;

        } else {
            for (int i = 0; i < (array.length / 2 + 1); i++) {
                for (int j = 0; j < (array.length / 2); j++) {
                    int temp = array[i][j];
                    array[i][j] = array[array.length - 1 - j][i];
                    array[array.length - 1 - j][i] = array[array.length - 1 - i][array.length - 1 - j];
                    array[array.length - 1 - i][array.length - 1 - j] = array[j][array.length - 1 - i];
                    array[j][array.length - 1 - i] = temp;
                }
            }
            return array;
        }
    }
}
