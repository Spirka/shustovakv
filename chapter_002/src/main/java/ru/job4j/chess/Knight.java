package ru.job4j.chess;
/**
 * Class Knight.
 * @author  shustovakv
 * @since 11.01.2018
 */
public class Knight extends Figure {
    /**
     * Constructor.
     */
    public Knight(Cell position) {
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
        int sourceX = source.getX();
        int sourceY = source.getY();
        int destX = dest.getX();
        int destY = dest.getY();
        Cell[] cells = null;

        if ((Math.abs(destX - sourceX) == 1 && Math.abs(destY - sourceY) == 2) || (Math.abs(destX - sourceX) == 2 && Math.abs(destY - sourceY) == 1)) {
            cells = new Cell[]{new Cell(destX, destY)};
        }
        return cells;
    }
    /**
     * Method Figure copy.
     *
     * @param dest destination position figure.
     * @return new king at the destination position..
     */
    @Override
    public Figure copy(Cell dest) {
        return new King(dest);
    }
}
