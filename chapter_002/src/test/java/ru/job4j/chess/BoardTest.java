package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class Test Chess board.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 26.12.2017
 */
public class BoardTest {
    /**
     * Test exception FigureNotFoundException.
     */
    @Test (expected = FigureNotFoundException.class)
    public void whenFigureNotFoundOnBoardException() {
        Board board = new Board();
        board.getFigures()[1][3] = new Bishop(new Cell(1, 3));
        board.move(new Cell(3, 2), new Cell(3, 1));
    }
    /**
     * Test exception ImpossibleMoveException.
     */
    @Test (expected = ImpossibleMoveException.class)
    public void whenFigureImpossibleMoveException() {
        Board board = new Board();
        board.getFigures()[1][3] = new Bishop(new Cell(1, 3));
        board.move(new Cell(1, 3), new Cell(2, 3));
    }
    /**
     * Test exception if another figure stands in the way.
     */
    @Test (expected = OccupiedWayException.class)
    public void whenOccupiedWayException() {
        Board board = new Board();
        board.getFigures()[1][3] = new Bishop(new Cell(1, 3));
        board.getFigures()[3][1] = new Bishop(new Cell(2, 2));
        board.move(new Cell(1, 3), new Cell(3, 1));
    }
    /**
     * Test exception if the other figure is at the destination.
     */
    @Test (expected = OccupiedWayException.class)
    public void whenOtherFigureIsAtTheDestination() {
        Board board = new Board();
        board.getFigures()[2][0] = new Bishop(new Cell(2, 0));
        board.getFigures()[7][5] = new Bishop(new Cell(7, 5));
        board.move(new Cell(2, 0), new Cell(7, 5));
    }
    /**
     * Test exception if going out of the chess field.
     */
    @Test (expected = ImpossibleMoveException.class)
    public void whenFigureGoingOutOfTheChessBoard() {
        Board board = new Board();
        board.getFigures()[0][2] = new Bishop(new Cell(0, 2));
        board.move(new Cell(0, 2), new Cell(6, 8));
    }
    /**
     * Test the move of the bishop.
     */
    @Test
    public void whenTheBishopMoveOnBoard() {
        Bishop bishop = new Bishop(new Cell(0, 2));
        Cell[] result = bishop.way(new Cell(0, 2), new Cell(4, 6));
        Cell[] expected = {new Cell(1, 3), new Cell(2, 4), new Cell(3, 5), new Cell(4, 6)};
        assertThat(result, is(expected));
    }
    /**
     * Test the move of the boat.
     */
    @Test
    public void whenTheBoatMoveOnBoard() {
        Boat boat = new Boat(new Cell(0, 7));
        Cell[] result = boat.way(new Cell(0, 7), new Cell(4, 7));
        Cell[] expected = {new Cell(1, 7), new Cell(2, 7), new Cell(3, 7), new Cell(4, 7)};
        assertThat((result), is(expected));
    }
    /**
     * Test the move of the queen.
     */
    @Test
    public void whenTheQueenMoveOnBoard() {
        Queen queen = new Queen(new Cell(0, 3));
        Cell[] result = queen.way(new Cell(0, 3), new Cell(4, 7));
        Cell[] expected = {new Cell(1, 4), new Cell(2, 5), new Cell(3, 6), new Cell(4, 7)};
        assertThat((result), is(expected));
    }
    /**
     * Test the move of the king.
     */
    @Test
    public void whenTheKingMoveOnBoard() {
        King king = new King(new Cell(0, 4));
        Cell[] result = king.way(new Cell(0, 4), new Cell(1, 5));
        Cell[] expected = {new Cell(1, 5)};
        assertThat((result), is(expected));
    }
    /**
     * Test the move of the knight.
     */
    @Test
    public void whenTheKnightMoveOnBoard() {
        Knight knight = new Knight(new Cell(4, 3));
        Cell[] result = knight.way(new Cell(4, 3), new Cell(2, 4));
        Cell[] expected = {new Cell(2, 4)};
        assertThat((result), is(expected));
    }
}
