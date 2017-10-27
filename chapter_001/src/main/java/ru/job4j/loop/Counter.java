package ru.job4j.loop;
/**
 * Class Counter.
 * @author shustovakv
 * @since 25.10.2017
 */
 public class Counter {
	 /**
	  * Поле result.
	  */
	 private int result = 0;
	 /**
	  * add.
	  * @param start начальное число.
	  * @param finish конечное число.
	  * @return сумма всех четных чисел от start до finish.
	  */
	 public int add(int start, int finish) {
		 for (int i = start; i <= finish; i++) { //внешний цикл.
			if (i % 2 == 0) {
				this.result += i;
			}
		 }
		 return result;
	}
}