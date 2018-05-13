package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test Class Bank.
 * @author  shustovakv
 * @since 06.05.2018
 */
public class BankTest {

    @Test
    public void whenBankAddUser() {
        Bank bank = new Bank();
        User user = new User("Ivan", "1234 567891");
        bank.addUser(user);
        assertTrue(bank.getMaps().containsKey(user));
    }

    @Test
    public void whenBankDeleteUser() {
        Bank bank = new Bank();
        User user = new User("Ivan", "1234 567891");
        bank.addUser(user);
        bank.deleteUser(user);
        assertTrue(bank.getMaps().isEmpty());
    }

    @Test
    public void whenBankAddAccountToUser() throws Bank.BankException {
        Bank bank = new Bank();
        User user = new User("Ivan", "1234 567891");
        bank.addUser(user);
        Account account = new Account(35.456, "12345");
        bank.addAccountToUser("1234 567891", account);
        assertTrue(bank.getUserAccounts("1234 567891").contains(account));
    }

    @Test
    public void whenBankDeleteAccountFromUser() throws Bank.BankException {
        Bank bank = new Bank();
        String passport = "1234 567891";
        User first = new User("Ivan", passport);
        bank.addUser(first);
        Account firstAc = new Account(50000.00, "12378");
        Account secondAc = new Account(10, "54321");
        bank.addAccountToUser(passport, firstAc);
        bank.addAccountToUser(passport, secondAc);
        List<Account> result = new ArrayList<>();
        result.add(secondAc);
        bank.deleteAccountFromUser(passport, firstAc);
        assertThat(bank.getUserAccounts(passport), is(result));
    }

    @Test
    public void whenBankShowUserAccounts() throws Bank.BankException {
        Bank bank = new Bank();
        String passport = "1234 567891";
        User user = new User("Ivan", passport);
        bank.addUser(user);
        Account firstAc = new Account(50000.00, "12378");
        Account secondAc = new Account(10, "54321");
        bank.addAccountToUser(passport, firstAc);
        bank.addAccountToUser(passport, secondAc);
        List<Account> result = new ArrayList<>();
        result.add(firstAc);
        result.add(secondAc);
        assertThat(bank.getUserAccounts(passport), is(result));
    }

    @Test
    public void whenBankTransferMoneyFromFirstAccountToSecondAccount() throws Bank.BankException {
        Bank bank = new Bank();
        String passport = "1234 567891";
        User user = new User("Ivan", passport);
        bank.addUser(user);
        Account firstAc = new Account(50000.00, "12378");
        Account secondAc = new Account(0, "54321");
        bank.addAccountToUser(passport, firstAc);
        bank.addAccountToUser(passport, secondAc);
        bank.transferMoney(passport, "12378", passport, "54321", 25000.00);
        assertThat(bank.findAccount(passport, "54321").getValue(), is(25000.00));
    }
}