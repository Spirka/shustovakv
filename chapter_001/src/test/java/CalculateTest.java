package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Test.
 *
 *@author Kseniya Shustova (shustovakv@mail.ru)
 *@version $Id$
 *@since 0.1
 */
public class CalculateTest {
	/**
	 * Test add.
	 */
	 @Test
	 public void whenTakeNameThenTreeEchoPlusName() {
		 String input = "Kseniya Shustova";
		 String expect = "Echo, echo, echo : Kseniya Shustova";
		 Calculate calc = new Calculate();
		 String result = calc.echo(input); 
		 assertThat(result, is(expect));
		
	 }
	
}