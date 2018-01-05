package ru.job4j.pseudo;
/**
 * Class Square.
 * @author  shustovakv
 * @since 06.12.2017
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++");
        pic.append(System.lineSeparator());
        pic.append("+    +");
        pic.append(System.lineSeparator());
        pic.append("+    +");
        pic.append(System.lineSeparator());
        pic.append("++++");
        return pic.toString();
    }
    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Square());
    }
}
