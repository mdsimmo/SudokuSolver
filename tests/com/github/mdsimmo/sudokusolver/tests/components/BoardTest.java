package com.github.mdsimmo.sudokusolver.tests.components;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testMakeBoard() throws Exception {
        Board board = new Board( 4 );
        assertEquals( 4, board.size() );
        assertEquals( 2, board.subSize() );
        assertNotNull( board.getCell( 1, 1 ) );
        assertNotNull( board.getCell( 4, 4 ) );
    }

    @Test
    public void testMakeInvalidBoard() {
        try {
            new Board( 6 );
            fail();
        } catch ( IllegalArgumentException ignored ) {
        }
        try {
            new Board( -9 );
            fail();
        } catch ( IllegalArgumentException ignored ) {

        }
    }

    @Test
    public void testGetCells() throws Exception {
        Board board = new Board( 9 );
        try {
            board.getCell( 0, 0 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {}
        try {
            board.getCell( 10, 10 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {}
        assertNotNull( board.getCell( 1, 1 ));
        assertNotNull( board.getCell( 9, 9 ));
    }

    @Test
    public void testDefault() throws Exception {
        Board board = new Board();
        assertEquals( 9, board.size() );
    }

    @Test
    public void testGetCol() {
        Board board = new Board();
        assertEquals( board.getCell( 1, 1 ), board.getColumn( 1 ).getCell( 1 ) );
        assertEquals( board.getCell( 7, 4 ), board.getColumn( 7 ).getCell( 4 ) );
        assertEquals( board.getCell( 9, 4 ), board.getColumn( 9 ).getCell( 4 ) );
        try {
            board.getColumn( 0 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
        try {
            board.getColumn( 10 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
    }

    @Test
    public void testGetRow() {
        Board board = new Board();
        assertEquals( board.getCell( 1, 1 ), board.getRow( 1 ).getCell( 1 ) );
        assertEquals( board.getCell( 7, 4 ), board.getRow( 4 ).getCell( 7 ) );
        assertEquals( board.getCell( 9, 4 ), board.getRow( 4 ).getCell( 9 ) );
        try {
            board.getColumn( 0 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
        try {
            board.getColumn( 10 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
    }

    @Test
    public void testGetSquare() {
        Board board = new Board( 9 );
        assertEquals( board.getCell( 1, 1 ), board.getSubGrid( 1 ).getCell( 1 ) );
        assertEquals( board.getCell( 9, 9 ), board.getSubGrid( 9 ).getCell( 9 ) );
        assertEquals( board.getCell( 1, 2 ), board.getSubGrid( 1 ).getCell( 4 ) );
        assertEquals( board.getCell( 3, 3 ), board.getSubGrid( 1 ).getCell( 9 ) );
        assertEquals( board.getCell( 5, 5 ), board.getSubGrid( 5 ).getCell( 5 ) );
        assertEquals( board.getCell( 7, 4 ), board.getSubGrid( 6 ).getCell( 1 ) );
        assertEquals( board.getCell( 8, 6 ), board.getSubGrid( 6 ).getCell( 8 ) );
        assertEquals( board.getCell( 3, 8 ), board.getSubGrid( 7 ).getCell( 6 ) );
        try {
            board.getColumn( 0 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
        try {
            board.getColumn( 10 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
    }

    @Test
    public void testIterator() {
        Board board = new Board( 9 );
        int i = 0;
        for ( Cell cell : board ) {
            i++;
            assertNotNull( cell );
        }
        assertEquals( 81, i );
    }
}