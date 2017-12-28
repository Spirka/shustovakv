package ru.job4j.chess;
/**
 * Class Board.
 * @author  shustovakv
 * @since 18.12.2017
 */
public class Board {
    /**
     * Array figure.
     */
    private Figure[][] figures = new Figure[8][8];
    /**
     *
     * @param source source position figure.
     * @param dest destination position figure
     * @return true.
     * @throws ImpossibleMoveException
     * @throws OccupiedWayException
     * @throws FigureNotFoundException
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        Figure value = figures[source.getX()][source.getY()];

        if (value == null) {
            throw new FigureNotFoundException("Figure not found.");
        }

        if (dest.getX() > figures.length - 1 || dest.getY() > figures.length - 1) {
            throw new ImpossibleMoveException("Going out of the chess field.");
        }

        Cell[] way = value.way(source, dest);
        if (way == null) {
            throw new ImpossibleMoveException("Figure can't go.");
        }

        if (!checkWay(way)) {
            throw new OccupiedWayException("Way busy other figure");
        }
        figures[source.getX()][source.getY()] = null;
        figures[dest.getX()][dest.getY()] = value.copy(dest);

        return true;

    }
    /**
     * Method check wey.
     * @param cells wey.
     * @return true if wey free.
     */
    private boolean checkWay(Cell[] cells) {
        boolean result = true;
        for (Cell cell : cells) {
                if (figures[cell.getX()][cell.getY()] != null) {
                    result = false;
                    break;
                }
            } return result;
        }

    public Figure[][] getFigures() {
        return figures;
    }
}
