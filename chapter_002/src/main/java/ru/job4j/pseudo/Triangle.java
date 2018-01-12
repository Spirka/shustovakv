package ru.job4j.pseudo;


/**
 * Class Triangle.
 * @author  shustovakv
 * @since 06.12.2017
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        int h = 3;
        StringBuilder pic = new StringBuilder();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < h * 2 - 1; j++) {
                if (j > h - 2 - i && j < h + i) {
                    pic.append("^");
                } else {
                    pic.append(" ");
                }
            }
            if (i < h - 1) {
                pic.append(System.lineSeparator());
            }
        }
        return pic.toString();
    }
    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
    }
}
