package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckPrimeNumberTest {

    @Test
    public void whenCheck1ThenReturnFalse() {
        CheckPrimeNumber checkPrimeNumber = new CheckPrimeNumber();
        boolean result = checkPrimeNumber.check(1);
        assertThat(result, is(false));
    }

    @Test
    public void whenCheck3ThenReturnTrue() {
        CheckPrimeNumber checkPrimeNumber = new CheckPrimeNumber();
        boolean result = checkPrimeNumber.check(3);
        assertThat(result, is(true));
    }

    @Test
    public void whenCheck4ThenReturnFalse() {
        CheckPrimeNumber checkPrimeNumber = new CheckPrimeNumber();
        boolean res = checkPrimeNumber.check(4);
        assertThat(res, is(false));
    }

    @Test
    public void whenCheck5ThenReturnTrue() {
        CheckPrimeNumber checkPrimeNumber = new CheckPrimeNumber();
        boolean result = checkPrimeNumber.check(5);
        assertThat(result, is(true));
    }

}