package ru.job4j.loop;

/**
 * Class CheckPrimeNumber.
 *
 * @author shustovakv
 * @since 29.11.2019
 */
class CheckPrimeNumber {

    boolean check(int num) {
        boolean prime = true;
        if (num < 2) {
            prime = false;
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                prime = false;
            }
        }
        return prime;
    }
}
