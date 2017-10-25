package ru.job4j.max;
/**
 *Class Max.
 *@author shustovakv
 *@since 19.10.2017
 */
public class Max {
	/**
     * max.
     * @param first переменная может быть больше или меньше second, third.
     * @param second переменная может быть больше или меньше first, third.
	 * @param third переменная может быть больше или меньше first, second. 
	 * @return result.
     */
	public int max(int first, int second, int third) {
		return Math.max(first, Math.max(second, third));
	}
}