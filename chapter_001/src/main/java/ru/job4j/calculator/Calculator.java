package ru.job4j.calculator;
/**
 *Class Calculator.
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
     * @param first переменная должна
     * складываться и записывать результат в поле this.result.
     * @param second переменная должна
     * складываться и записывать результат в поле this.result.
     */
    public void add(double first, double second) {

        this.result = first + second;
    }

    /**
     * subtract.
     * @param second переменная и должна вычитаться
     * из переменной first и записывать результат в поле this.result.
     * @param first из этой переменной вычитается переменная second.
     */
    public void subtract(double first, double second) {

        this.result = first - second;
    }
    /**
     * div.
     * @param first переменная и должна делиться
     * на переменную second и записывать результат в поле this.result.
     * @param second это переменная на которую делится first.
     */
    public void div(double first, double second) {

        this.result = first / second;
    }
    /**
     * multiple.
     * @param first и second переменные должны
     * перемножаться и записывать результат в поле this.result.
     * @param second умножается на first.
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