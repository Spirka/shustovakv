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
        pic.append("+    +");
        pic.append("+    +");
        pic.append("++++");
        return pic.toString();
    }
}
