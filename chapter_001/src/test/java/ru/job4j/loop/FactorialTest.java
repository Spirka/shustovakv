package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test.
 *
 *@author Kseniya Shustova (shustovakv@mail.ru)
 *@version $Id$
 *@since 0.1
 */
 public class FactorialTest {
	 /**
	 * Test on factorial 5.
	 */
	 @Test
	 public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
		 Factorial factorial = new Factorial();
		 int result = factorial.calc(5);
		 assertThat(result, is(120));
	 }
	 /**
	  * Test on factorial 0.
	  */
	  @Test
	  public void whenCalculateFactorialForZeroThenOne() {
		  Factorial factorial = new Factorial();
		  int result = factorial.calc(0);
		  assertThat(result, is(1));
	  }
 }
