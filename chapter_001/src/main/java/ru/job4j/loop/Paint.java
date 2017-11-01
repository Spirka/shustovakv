package ru.job4j.loop;
/**
 * Class Paint.
 * @author  shustovakv
 * @since 31.10.2017
 */
 public class Paint {
	 /**
	  * piramid.
	  * @param h высота пирамиды.
	  * @return.
	  */
	  public String piramid(int h) {
		  StringBuilder builder = new StringBuilder();
		  for (int i = 0; i < h; i++) {
			  for (int j = 0; j <= h * 2 - 2; j++) {
				  if (j > h - 2 - i && j < h + i) {
				  builder.append("^");
				  } else {
					  builder.append(" ");
				  }
			  }
			  builder.append("\r\n");
		  }
		  return builder.toString();
	  }
 }