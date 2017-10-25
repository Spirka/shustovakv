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
	private double x;
	/**
	 * Полe y.
	 */
	private double y;
	/**
	 * point.
	 * @param x определяет положение точки по оси Х
	 * и записывает результат в поле this.x.
	 * @param y определяет положение точки по оси У
	 * и записывает результат в поле this.y.
	 */
	 public Point(double x, double y) {
		this.x = x;
		this.y = y;
	 }
	 /**
	  * Method getX.
	  * @return this.x.
	  */
	  public double getX() {
		return this.x;
	  }
	  /**
	   * Method getY.
	   * @return this.y.
	   */
	  public double getY() {
		return this.y;
	  }
	  /**
	   * Method is.
	   * @param a это.
	   * @param b это.
	   * @return result.
	   */
	   public boolean is(double a, double b) {
		   return this.y == this.x * a + b;
		}
}