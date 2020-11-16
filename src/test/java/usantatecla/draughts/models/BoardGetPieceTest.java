package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardGetPieceTest {
    Board sut;

    @Before
    public void setUp() {
        sut = new Board();
    }

    @Test(expected = AssertionError.class)
    public void whenCoordinateIsNullThrowsError() {
        sut.getPiece(null);
    }

    @Test
    public void whenThereIsNoPieceThenReturnNull() {
        assertNull(sut.getPiece( new Coordinate(2,3)));
    }

    @Test
    public void whenThereIsAPieceTheReturnThatPiece() {
        arrangePiece(4,5, new Pawn(Color.WHITE));

        Piece actualPiece = sut.getPiece(new Coordinate(4, 5));

        assertEquals(new Pawn(Color.WHITE), actualPiece);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenRequestPieceOutOfUpperBoardBoundThenThrowsException() {
        Piece actualPiece = sut.getPiece(new Coordinate(Coordinate.getDimension(), Coordinate.getDimension()));
        assertNull(actualPiece);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenRequestPieceOutOfLowerBoardBoundThenThrowsException() {
        Piece actualPiece = sut.getPiece(new Coordinate(-1, -1));
        assertNull(actualPiece);
    }

    private void arrangePiece(int col, int row, Piece piece) {
        sut.put(new Coordinate(col, row), piece);
    }
}
