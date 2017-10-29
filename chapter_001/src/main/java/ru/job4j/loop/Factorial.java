package ru.job4j.loop;
/**
 * Class Factorial.
 * @author shustovakv
 * @since 29.10.2017
 */
 public class Factorial {
	 /**
	  * Поле result.
	  */
	  private int result = 1;
	  /**
	  * calc.
	  * @param n целое положительное число.
	  * @return расчитанный факториал числа n.
	  */
	  public int calc(int n) {
		  for (int i = 1; i <= n; i++) {
			  if (n > 0) {
				  this.result *= i;
			  }
			}
		  return result;
	  }
 }