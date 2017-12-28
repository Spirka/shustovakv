package ru.job4j.chess;

/**
 * Abstract Class Figure.
 * @author  shustovakv
 * @since 17.12.2017
 */
abstract class Figure {
    /**
     * Position figure.
     */
    final Cell position;
    /**
     * Color of a chess piece.
     */
    public String color;
    /**
     * Constructor Figure.
     * @param position initial position figure.
     */
    Figure(Cell position) {
        this.position = position;
    }

    /**
     * Abstract method wey figure.
     * @param source source position figure.
     * @param dest destination position figure.
     * @return Array cell wey.
     * @throws ImpossibleMoveException
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException;

    /**
     * Abstract method Figure copy.
     * @param dest destination position figure.
     * @return figure.
     */
    public abstract Figure copy(Cell dest);

    /**
     *
     * @return
     */
    public Cell getPosition() {
        return position;
    }
}