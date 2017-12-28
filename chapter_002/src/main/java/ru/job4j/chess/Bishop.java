package ru.job4j.chess;

public class Bishop extends Figure {

     public Bishop(Cell position) {
        super(position);
    }
    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int sourceX = source.getX();
        int sourceY = source.getY();
        int destX = dest.getX();
        int destY = dest.getY();
        Cell[] result = null;

        if (Math.abs(destX - sourceX) == Math.abs(destY - sourceY)) {
            int countStep = Math.abs(destX - sourceX);
            Cell[] cells = new Cell[countStep];
            int stepX = (sourceX < destX) ? 1 : -1;
            int stepY = (sourceY < destY) ? 1 : -1;
            for (int i = 0; i < countStep; i++) {
                sourceX += stepX;
                sourceY += stepY;
                cells[i] = new Cell(sourceX, sourceY);
            }
            result = cells;
        }
        return result;
    }
}
