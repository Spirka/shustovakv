package ru.job4j.chess;

import org.junit.Test;

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
}
