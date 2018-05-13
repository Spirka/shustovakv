package ru.job4j.bank;

import java.util.Objects;

/**
 * Class Account.
 * @author  shustovakv
 * @since 06.05.2018
 */
public class Account {

    /**
     * Field value.
     */
    private double value;

    /**
     * Field requisites.
     */
    private String requisites;

    /**
     * Constructor.
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Method transfer money.
     */
    public boolean transferMoney(double amount, Account dest) {
        if (this.value > amount && dest != null) {
            dest.value += amount;
            this.value -= amount;
            return true;
        }
        return false;
    }

    /**
     * Method check requisite
     * @param requisite
     * @return
     */
    public boolean checkRequisite(String requisite) {
        return Objects.equals(requisite, this.requisites);
    }

    /**
     * getter
     * @return
     */
    public double getValue() {
        return value;
    }
}
