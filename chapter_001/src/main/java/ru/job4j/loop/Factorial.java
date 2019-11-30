package ru.job4j.loop;
/**
 * Class Factorial.
 * @author shustovakv
 * @since 29.10.2017
 */
class Factorial {
	 /**
	  * Поле result.
	  */
	  private int result = 1;
	  /**
	  * calc.
	  * @param n целое положительное число.
	  * @return расчитанный факториал числа n.
	  */
      int calc(int n) {
		  for (int i = 1; i <= n; i++) {
			  this.result *= i;
		  }
		  return result;
	  }
 }