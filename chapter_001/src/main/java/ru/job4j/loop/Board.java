package ru.job4j.loop;
/**
 * Class Board.
 * @author shustovakv
 * @since 30.10.2017
 */
 public class Board {
	  /**
	  * paint.
	  * @param width ширина доски.
	  * @param height высота доски.
	  * @return.
	  */
	  public String paint(int width, int height) {
		  StringBuilder builder = new StringBuilder();
		  //внешний цикл отвечает за высоту доски.
		  for (int i = 1; i <= height; i++) {
			  //внутренний цикл отвечает за ширину доски.
			  for (int j = 0; j < width; j++) {
				  if ((i + j) % 2 == 0) {
					  builder.append(" ");
				  } else {
					  builder.append("x");
					 }
			  }
			  builder.append(System.lineSeparator());
		  }
		  return builder.toString();
	  }
 }