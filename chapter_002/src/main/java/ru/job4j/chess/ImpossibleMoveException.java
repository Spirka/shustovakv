package ru.job4j.chess;
/**
 * Class ImpossibleMoveException.
 * @author  shustovakv
 * @since 17.12.2017
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Constructor ImpossibleMoveException exception.
     * @param msg massage exception.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }

}
