package ru.job4j.bank;

import java.util.*;

/**
 * Class Bank.
 *
 * @author shustovakv
 * @since 06.05.2018
 */
public class Bank {

    /**
     * Field maps.
     */
    private Map<User, List<Account>> maps = new HashMap<>();

    /**
     * Method getter.
     * @return maps
     */
    public Map<User, List<Account>> getMaps() {
        return maps;
    }

    /**
     * Method add user.
     * Добавление пользователя.
     */
    public void addUser(User user) {

        this.maps.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Method delete user.
     * Удаление пользователя.
     */
    public void deleteUser(User user) {

        this.maps.remove(user);
    }

    /**
     * Method add account to user.
     * Добавить счёт пользователю.
     */
    public void addAccountToUser(String passport, Account account) throws BankException {

        getUserAccounts(passport).add(account);
    }

    /**
     * Method delete account from user.
     * Удалить один счёт пользователя.
     */
    public void deleteAccountFromUser(String passport, Account account) throws BankException {

       getUserAccounts(passport).remove(account);
    }

    /**
     * Method get user accounts.
     * Получить список счетов для пользователя.
     *
     * @return List<Account>
     */
    public List<Account> getUserAccounts(String passport) throws BankException {

        User user = null;
        for (User element : this.maps.keySet()) {
            if (element.checkPassport(passport)) {
                user = element;
                break;
            }
        }
        if (user == null) {
            throw new BankException("пользователь не найден");
        }
        return this.maps.get(user);
    }

    /**
     * Method find account.
     */
    public Account findAccount(String passport, String requisite) throws BankException {

        Account account = null;
        for (Account element : getUserAccounts(passport)) {
            if (element.checkRequisite(requisite)) {
                account = element;
                break;
            }
        }
        if (account == null) {
            throw new BankException("Счет не найден");
        }
        return account;
    }

    /**
     * Method transfer money.
     * Метод для перечисления денег с одного счёта на другой счёт:
     * если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) throws BankException {

        Account src = findAccount(srcPassport, srcRequisite);
        Account dest = findAccount(destPassport, destRequisite);
        return src.transferMoney(amount, dest);
    }

    /**
     * Class Bank exception.
     */
    public static class BankException extends Exception {
        BankException(String msg) {
            super(msg);
        }
    }
}
