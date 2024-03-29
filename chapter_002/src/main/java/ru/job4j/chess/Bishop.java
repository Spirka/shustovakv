package ru.job4j.chess;
/**
 * Class Bishop.
 * @author  shustovakv
 * @since 18.12.2017
 */
public class Bishop extends Figure {
    /**
     * Constructor.
     * @param position
     */
     public Bishop(Cell position) {
        super(position);
    }

    /**
     * Method copy.
     * @param dest destination position figure.
     * @return new bishop at the destination position.
     */
    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

    /**
     * Method way.
     * @param source source position figure.
     * @param dest destination position figure.
     * @return Array cell way.
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        int currentX = source.getX();
        int currentY = source.getY();
        int destX = dest.getX();
        int destY = dest.getY();
        Cell[] result = null;

        if (Math.abs(destX - currentX) == Math.abs(destY - currentY)) {
            int countStep = Math.abs(destX - currentX);
            Cell[] cells = new Cell[countStep];
            int stepX = (currentX < destX) ? 1 : -1;
            int stepY = (currentY < destY) ? 1 : -1;
            for (int i = 0; i < countStep; i++) {
                currentX += stepX;
                currentY += stepY;
                cells[i] = new Cell(currentX, currentY);
            }
            result = cells;
        }
        return result;
    }
}
