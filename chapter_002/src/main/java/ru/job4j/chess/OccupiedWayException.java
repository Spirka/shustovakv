package ru.job4j.chess;
/**
 * Class ImpossibleMoveException.
 * @author  shustovakv
 * @since 18.12.2017
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Constructor ImpossibleMoveException exception.
     * @param msg massage exception.
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
