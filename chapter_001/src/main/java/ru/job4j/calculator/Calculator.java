package ru.job4j.calculator;
/**
 *Class Calculate решение задачи части 001 урок 1.
 *@author shustovakv
 *@since 17.10.2017
 */
public class Calculator {
    /**
     * Результат.
     */
    private double result;
    /**
     * add.
     * @param first, second переменные должны
     * складываться и записывать результат в поле this.result.
     */
    public void add(double first, double second) {

        this.result = first + second;
    }

    /**
     * subtract.
     * @param second переменная , должна вычитаться
     * из переменной first и записывать результат в поле this.result.
     */
    public void subtract(double first, double second) {

        this.result = first - second;
    }
    /**
     * div.
     * @param first переменная , должна делиться
     * на переменную second и записывать результат в поле this.result.
     */
    public void div(double first, double second) {

        this.result = first / second;
    }
    /**
     * multiple.
     * @param first, second переменные должны
     * перемножаться и записывать результат в поле this.result.
     */
    public void multiple(double first, double second) {

        this.result = first * second;
    }
    /**
     * Method getResult.
     * @return result.
     */
    public double getResult() {

        return result;
    }
}