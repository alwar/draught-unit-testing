package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardPutTest {
    Board sut;

    @Before
    public void setUp() {
        sut = new Board();
    }

    @Test(expected = AssertionError.class)
    public void testWhenPutOnNullCoordinateThrowsException() {
        sut.put(null, new Draught(Color.WHITE));
    }

    @Test
    public void whenPutNullPieceThenNullPieceIsStored() {
        sut.put(new Coordinate(2,6), null);

        assertNull(sut.getPiece(new Coordinate(2,6)));
    }

    @Test
    public void whenPutDraughtThenPieceIsStored() {
        Coordinate coordinateWhite = new Coordinate(2, 6);
        Coordinate coordinateBlack = new Coordinate(4, 3);

        sut.put(coordinateWhite, new Draught(Color.WHITE));
        sut.put(coordinateBlack, new Draught(Color.BLACK));

        assertEquals(new Draught(Color.WHITE), sut.getPiece(coordinateWhite));
        assertEquals(new Draught(Color.BLACK), sut.getPiece(coordinateBlack));
    }

    @Test
    public void whenPutPawnThenPieceIsStored() {
        Coordinate coordinateWhite = new Coordinate(2, 6);
        Coordinate coordinateBlack = new Coordinate(4, 3);

        sut.put(coordinateWhite, new Pawn(Color.WHITE));
        sut.put(coordinateBlack, new Pawn(Color.BLACK));

        assertEquals(new Pawn(Color.WHITE), sut.getPiece(coordinateWhite));
        assertEquals(new Pawn(Color.BLACK), sut.getPiece(coordinateBlack));
    }
}
