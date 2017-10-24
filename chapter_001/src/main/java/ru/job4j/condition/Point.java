package ru.job4j.condition;
/**
 *Class Point.
 *@author shustovakv
 *@since 20.10.2017
 */
public class Point {
	/**
	 * Полe x.
	 */
	private int x;
	/**
	 * Полe y.
	 */
	private int y;
	/**
	 * point.
	 * @param x определяет положение точки по оси Х
	 * и записывает результат в поле this.x.
	 * @param y определяет положение точки по оси У
	 * и записывает результат в поле this.y.
	 */
	 public Point(int x, int y) {
		this.x = x;
		this.y = y;
	 }
	 /**
	  * Method getX.
	  * @return this.x.
	  */
	  public int getX() {
		return this.x;
	  }
	  /**
	   * Method getY.
	   * @return this.y.
	   */
	  public int getY() {
		return this.y;
	  }
	  /**
	   * Method is.
	   * @param a это.
	   * @param b это.
	   * @return result.
	   */
	   public boolean is(int a, int b) {
		   if (y == a * x + b) {
		   return true;
		   }
			else {
			return false;
			}
		}
}