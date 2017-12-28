package ru.job4j.chess;
/**
 * Class FigureNotFoundException.
 * @author  shustovakv
 * @since 18.12.2017
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Constructor ImpossibleMoveException exception.
     * @param msg massage exception.
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
