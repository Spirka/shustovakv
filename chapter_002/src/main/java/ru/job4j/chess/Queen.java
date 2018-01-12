package ru.job4j.chess;
/**
 * Class Boat.
 * @author  shustovakv
 * @since 05.01.2018
 */
public class Queen extends Figure {
    /**
     * Bishop.
     */
    private static final Bishop BISHOP = new Bishop(new Cell(0, 3));
    /**
     * Boat.
     */
    private static final Boat BOAT = new Boat(new Cell(0, 3));

    /**
     * Constructor.
     *
     * @param position
     */
    public Queen(Cell position) {
        super(position);
    }

    /**
     * Method wey figure.
     *
     * @param source source position figure.
     * @param dest   destination position figure.
     * @return Array cell wey.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] result = BISHOP.way(source, dest);
        if (result == null) {
            result = BOAT.way(source, dest);
        }
        return result;
    }

    /**
     * Method Figure copy.
     *
     * @param dest destination position figure.
     * @return new queen at the destination position..
     */
    @Override
    public Figure copy(Cell dest) {
        return new Queen(dest);
    }
}