package ru.job4j.chess;

import java.util.Objects;

/**
 * Class Cell.
 * @author  shustovakv
 * @since 17.12.2017
 */
public class Cell {
    /**
     * Vertical board.
     */
    private int x;
    /**
     * Horizontal board.
     */
    private int y;
    /**
     * Constructor class Cell.
     * @param x initial vertical.
     * @param y initial horizontal.
     */
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
