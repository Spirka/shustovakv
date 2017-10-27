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
public class CounterTest {
    /**
	 * Test on add.
	 */
	@Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        //напишите здесь тест, провер¤ющий, что сумма чЄтных чисел от 1 
		//до 10 при вызове метода counter.add будет равна 30.
		//создаем объект класса Counter.
		Counter counter = new Counter();
		//передаем в метод add начальное и конечное значение.
		int result = counter.add(0, 10);
		//сравниваем результат с ожиданием.
		assertThat(result, is(30));
    }
}