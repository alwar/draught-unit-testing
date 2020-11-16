package usantatecla.draughts.models;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test(expected = AssertionError.class)
    public void whenGetInstanceFormatIsNullThenThrowException() {
        Coordinate.getInstance(null);
    }

    @Test
    public void whenGetInstanceFormatIsNotValidThenReturnNull() {
        assertNull(Coordinate.getInstance("asdf"));
    }

    @Test
    public void whenCoordinateIsNotWithinLimitsThenReturnNull() {
        int maxDimension = Coordinate.getDimension() + 1;
        assertNull(Coordinate.getInstance(maxDimension + "" + maxDimension));
    }

    @Test
    public void whenCoordinateIsValidThenReturnThatCoordinate() {
        Coordinate coord34 = Coordinate.getInstance("34");

        assertThat(coord34.getRow(), is(2));
        assertThat(coord34.getColumn(), is(3));
    }

    @Test
    public void whenCoordinateIsOnDiagonalThenReturnTrue() {
        assertTrue(Coordinate.getInstance("23").isOnDiagonal(Coordinate.getInstance("34")));
        assertTrue(Coordinate.getInstance("23").isOnDiagonal(Coordinate.getInstance("12")));
        assertFalse(Coordinate.getInstance("23").isOnDiagonal(Coordinate.getInstance("33")));
    }

    @Test(expected = AssertionError.class)
    public void whenGetBetweenDiagonalCoordinateDistanceIsNotTwoThenThrowsException() {
        Coordinate.getInstance("22").getBetweenDiagonalCoordinate(Coordinate.getInstance("55"));
    }

    @Test
    public void whenGetBetweenDiagonalCoordinateThenReturnCoordinate() {
        Coordinate coordinate1 = Coordinate.getInstance("22").getBetweenDiagonalCoordinate(Coordinate.getInstance("44"));
        assertEquals(coordinate1, Coordinate.getInstance("33"));

        Coordinate coordinate2 = Coordinate.getInstance("42").getBetweenDiagonalCoordinate(Coordinate.getInstance("24"));
        assertEquals(coordinate2, Coordinate.getInstance("33"));
    }

    @Test
    public void whenGetBetweenDiagonalCoordinatesThenReturnListOfCoordinates() {
        List<Coordinate> coordinateList = Coordinate.getInstance("11").getBetweenDiagonalCoordinates(Coordinate.getInstance("44"));

        assertEquals(coordinateList.size(), 2);
        assertTrue(coordinateList.stream().anyMatch(c -> c.equals(Coordinate.getInstance("22"))));
        assertTrue(coordinateList.stream().anyMatch(c -> c.equals(Coordinate.getInstance("33"))));
    }

    @Test(expected = AssertionError.class)
    public void whenGetBetweenDiagonalCoordinatesIsNotInDiagonalThenThrowsException() {
        Coordinate.getInstance("11").getBetweenDiagonalCoordinates(Coordinate.getInstance("54"));
    }

    @Test
    public void whenGetDirectionReturnDiagonalDirection() {
        Coordinate origin = Coordinate.getInstance("44");
        assertEquals(Direction.NE, origin.getDirection(Coordinate.getInstance("55")));
        assertEquals(Direction.NW, origin.getDirection(Coordinate.getInstance("53")));
        assertEquals(Direction.SE, origin.getDirection(Coordinate.getInstance("35")));
        assertEquals(Direction.SW, origin.getDirection(Coordinate.getInstance("33")));
    }

    @Test
    public void whenGetDirectionIsNotInDiagonalThenReturnNull() {
        Coordinate origin = Coordinate.getInstance("44");
        assertNull(origin.getDirection(Coordinate.getInstance("34")));
        assertNull(origin.getDirection(Coordinate.getInstance("43")));
        assertNull(origin.getDirection(Coordinate.getInstance("45")));
        assertNull(origin.getDirection(Coordinate.getInstance("54")));

    }

    @Test(expected = AssertionError.class)
    public void whenGetDiagonalDistanceIsNotInDiagonalThenThrowsException() {
        Coordinate.getInstance("44").getDiagonalDistance(Coordinate.getInstance("54"));
    }

    @Test
    public void whenGetDiagonalDistanceThenReturnDistance() {
        assertEquals(4, Coordinate.getInstance("44").getDiagonalDistance(Coordinate.getInstance("88")));
        assertEquals(3, Coordinate.getInstance("44").getDiagonalDistance(Coordinate.getInstance("17")));
    }

    @Test
    public void whenF() {
        List<Coordinate> diagonalCoordinates = Coordinate.getInstance("44").getDiagonalCoordinates(2);

        assertEquals(4, diagonalCoordinates.size());
        assertTrue(diagonalCoordinates.stream().anyMatch(c -> c.equals(Coordinate.getInstance("22"))));
        assertTrue(diagonalCoordinates.stream().anyMatch(c -> c.equals(Coordinate.getInstance("66"))));
        assertTrue(diagonalCoordinates.stream().anyMatch(c -> c.equals(Coordinate.getInstance("62"))));
        assertTrue(diagonalCoordinates.stream().anyMatch(c -> c.equals(Coordinate.getInstance("26"))));

    }
}
