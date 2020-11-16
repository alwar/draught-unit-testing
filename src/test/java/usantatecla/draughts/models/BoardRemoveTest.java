package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BoardRemoveTest {
    Board sut;

    @Before
    public void setUp() {
        sut = new Board();
    }

    @Test(expected = AssertionError.class)
    public void whenRemovePieceCoordinateIsNullThrowException() {
        sut.remove(null);
    }

    @Test(expected = AssertionError.class)
    public void whenRemovePieceIsEmptyThenThrowException() {
        sut.remove(new Coordinate(1,4));
    }

    @Test
    public void whenRemovePieceThenReturnThatPiece() {
        arrangePiece(3,6, new Draught(Color.BLACK));

        Piece removedPiece = sut.remove(new Coordinate(3,6));

        assertEquals(new Draught(Color.BLACK), removedPiece);
    }

    @Test
    public void whenRemovePieceThenTheCoordinateReturnNull() {
        arrangePiece(3,6, new Draught(Color.BLACK));

        sut.remove(new Coordinate(3,6));

        assertNull(sut.getPiece(new Coordinate(3,6)));
    }

    private void arrangePiece(int col, int row, Piece piece) {
        sut.put(new Coordinate(col, row), piece);
    }
}
