package ru.job4j.machine;
/**
 * Class CoffeeMachine.
 * @author  shustovakv
 * @since 01.02.2018
 */
public class CoffeeMachine {
    /**
     * Array coins.
     */
    private final int[] coins = {10, 5, 2, 1};

    /**
     * Method changes
     * @param value denomination.
     * @param price cost of coffee.
     * @return Array of coins.
     */
    public int[] changes(int value, int price) {
        int surrender = value - price;
        int[] count = new int[this.coins.length];
        int lengthResult = 0;
        for (int i = 0; i < this.coins.length; i++) {
            count[i] = surrender / this.coins[i];
            lengthResult += count[i];
            surrender %= this.coins[i];
            if (surrender == 0) {
                break;

            }
        }
        int[] result = new int[lengthResult];
        int index = 0;
        for (int i = 0; i < this.coins.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                result[index++] = this.coins[i];
            }
        }
        return result;
    }
}
