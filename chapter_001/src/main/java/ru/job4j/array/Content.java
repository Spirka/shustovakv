package ru.job4j.array;
/**
 * Class Content.
 * @author  shustovakv
 * @since 14.11.2017
 */
public class Content {
    /**
     * Contains.
     *
     * @param origin оригинальное слово.
     * @param sub    суррогатное слово.
     * @return содержит или нет.
     */
    public boolean contains(String origin, String sub) {
        char[] charsOrigin = origin.toCharArray();
        char[] charsSub = sub.toCharArray();
        boolean contains = true;

        for (int i = 0; i < charsOrigin.length; i++) {
            for (int j = 0; j < charsSub.length;) {
                if (charsOrigin[i] == charsSub[j]) {
                    boolean flag = true;
                    break;
                } else {
                    if (charsSub[j] != charsOrigin[j + i]) {
                    contains = false;
                    break;
                    }
                }
            }
        } return contains;
    }
}
