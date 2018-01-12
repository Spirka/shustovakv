package ru.job4j.chess;
/**
 * Class Boat.
 * @author  shustovakv
 * @since 03.01.2018
 */
public class Boat extends Figure {
    /**
     * Constructor.
     * @param position
     */
    public Boat(Cell position) {
        super(position);
    }

    /**
     *
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

        if (destX == currentX || destY == currentY) {
            int countStep = Math.abs(destX - currentX) + Math.abs(destY - currentY);
            Cell[] cells = new Cell[countStep];
            int stepX = Integer.compare(destX, currentX);
            int stepY = Integer.compare(destY, currentY);
            for (int i = 0; i < countStep; i++) {
                currentX += stepX;
                currentY += stepY;
                cells[i] = new Cell(currentX, currentY);
            }
            result = cells;
        }
        return result;
    }
    /**
     * Method copy.
     * @param dest destination position figure.
     * @return new boat at the destination position.
     */
    @Override
    public Figure copy(Cell dest) {
        return new Boat(dest);
    }
}
