package ru.job4j.condition;

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
public class PointTest {
	/**
	 * Test on line.
	 */
	 @Test
	public void whenPointOnLineThenTrue() {
		//create of new point.
		Point a = new Point(0, 1);
		// execute method - is and get result;
		boolean rsl = a.is(0, 1);
		// assert result by excepted value.
		assertThat(rsl, is(true));
   }
}

