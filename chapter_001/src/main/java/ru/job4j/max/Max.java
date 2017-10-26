package ru.job4j.max;
/**
 *Class Max.
 *@author shustovakv
 *@since 26.10.2017
 */
public class Max {
	/**
     * max.
     * @param first переменная может быть больше или меньше second.
     * @param second переменная может быть больше или меньше first.
	 * @return result.
     */
	public int max(int first, int second) {
		int result = (first > second) ? first : second;
		return result;
	}
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}