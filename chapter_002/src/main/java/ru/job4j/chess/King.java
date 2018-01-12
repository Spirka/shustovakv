package ru.job4j.chess;
/**
 * Class King.
 * @author  shustovakv
 * @since 10.01.2018
 */
public class King extends Figure {
    /**
     * Constructor.
     */
    public King(Cell position) {
        super(position);
    }
    /**
     * Abstract method wey figure.
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

        if (Math.abs(destX - sourceX) <= 1 && Math.abs(destY - sourceY) <= 1 && (destX != sourceX && destY != sourceY)) {
            cells = new Cell[]{new Cell(destX, destY)};
        }
        return cells;
    }

    /**
     * Abstract method Figure copy.
     *
     * @param dest destination position figure.
     * @return new king at the destination position..
     */
        @Override
        public Figure copy(Cell dest) {
            return new King(dest);
        }
}
