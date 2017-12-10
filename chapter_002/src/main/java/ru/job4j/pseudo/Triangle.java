package ru.job4j.pseudo;
/**
 * Class Triangle.
 * @author  shustovakv
 * @since 06.12.2017
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   +   \n");
        pic.append("  + +  \n");
        pic.append(" +   + \n");
        pic.append("+++++++");
        return pic.toString();
    }
    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
    }
}
