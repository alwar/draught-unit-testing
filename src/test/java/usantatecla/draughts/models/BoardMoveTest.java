package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardMoveTest {
    Board sut;

    @Before
    public void setUp() {
        sut = new Board();
    }

    @Test(expected = AssertionError.class)
    public void whenMoveOriginCoordinateIsNullThenThrowException() {
        sut.move(null, new Coordinate(3,2));
    }

    @Test(expected = AssertionError.class)
    public void whenMoveDestinationCoordinateIsNullThenThrwoException() {
        sut.move(new Coordinate(2,5), null);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenOriginCoordinateIsOutOfBoardThenThrowsException() {
        sut.move(new Coordinate(1,Coordinate.getDimension()+1), new Coordinate(2,3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenDestinationCoordinateIsOutOfBoardThenThrowException() {
        Coordinate origin = new Coordinate(1, Coordinate.getDimension() + 1);
        sut.put(origin, new Pawn(Color.WHITE));

        sut.move(origin, new Coordinate(2,3));
    }

    @Test(expected = AssertionError.class)
    public void whenOriginCoordinateIsEmptyThenThrowsException() {
        sut.move(new Coordinate(2,3), new Coordinate(1,1));
    }

    @Test
    public void whenOriginCoordinateIsNotEmptyThenMovePiece() {
        Coordinate origin = new Coordinate(2, 3);
        Coordinate target = new Coordinate(1, 1);

        sut.put(origin, new Pawn(Color.WHITE));

        sut.move(origin, target);

        assertNull(sut.getPiece(origin));
        assertEquals(new Pawn(Color.WHITE), sut.getPiece(target));
    }
}
