package ru.job4j.max;

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
public class MaxTest {
	/**
     * Test less.
     */
	@Test
	public void whenFirstLessSecondAndThird() {
		Max maxim = new Max();
		int result = maxim.max(1, 2, 3);
		assertThat(result, is(3));
	}
	/**
     * Test more.
     */
	@Test
	public void whenFirstMoreSecondAndThird() {
		Max maxim = new Max();
		int result = maxim.max(3, 2, 1);
		assertThat(result, is(3));
	}
}