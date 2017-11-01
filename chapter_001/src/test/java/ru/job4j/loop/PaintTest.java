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
 public class PaintTest {
	 /**
     * Test 2.
     */
	 @Test
	 public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
		 Paint paint = new Paint();
		 String result = paint.piramid(2);
		 final String line = System.getProperty("line.separator");
		 String expected = String.format(" ^ %s^^^%s", line, line);
		 assertThat(result, is(expected));
	 }
	 /**
	  * Test 3.
	  */
	  @Test
	  public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
		 Paint paint = new Paint();
		 String result = paint.piramid(3);
		 final String line = System.getProperty("line.separator");
		 String expected = String.format("  ^  %s ^^^ %s^^^^^%s", line, line, line);
		 assertThat(result, is(expected));
	  }
 }