package ru.job4j.condition;
/**
 * Class Triangle.
 * @author shustovakv
 * @since 24.10.2017
 */
 public class Triangle {
	 /**
	  * Поле a.
	  */
	  private Point a;
	 /**
	 * Поле b.
	 */
	  private Point b;
	 /**
	  * Поле c.
	  */
	  private Point c;
	  /**
	   * triange.
	   * @param a определяет положение точки a
	   * и записывает результат в поле this.a.
	   * @param b определяет положение точки b
	   * и записывает результат в поле this.b.
	   * @param c определяет положение точки c
	   * и записывает результат в поле this.c.
	   */
	   public Triangle(Point a, Point b, Point c) {
		   this.a = a;
		   this.b = b;
		   this.c = c;
	   }
	   /**
	    * distance.
		* @param left Точка слева.
		* @param right Точка справа.
		* @return расстояние между точками left и right.
		*/
		public double distance(Point left, Point right) {
			return Math.sqrt(Math.pow(left.getX() - right.getX(), 2) + Math.pow(left.getY() - right.getY(), 2));
		}
		/**
		 * period.
		 * @param ab расстояние между точками a b.
		 * @param ac расстояние между точками a c.
		 * @param bc расстояние между точками b c.
		 * @return Перимент.
		 */
		public double period(double ab, double ac, double bc) {
			 return (ab + ac + bc) / 2;
		 }
		/**
		  * area.
		  * @return Вернуть площадь, если треугольник существует или -1.
		  */
		public double area() {
			double rsl = -1;
			double ab = this.distance(a, b);
			double ac = this.distance(a, c);
			double bc = this.distance(b, c);
			double p = this.period(ab, ac, bc);
			if (this.exist(ab, ac, bc)) {
				rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
				}
				return rsl;
		}
		/**
		 * exist.
		 * @param ab Длина от точки a b.
		 * @param ac Длина от точки a c.
		 * @param bc Длина от точки b c.
		 * @return result.
		 */
		 private boolean exist(double ab, double ac, double bc) {
			 return (ab + ac) > bc && (ab + bc) > ac && (ac + bc) > ab;
		 }
 }