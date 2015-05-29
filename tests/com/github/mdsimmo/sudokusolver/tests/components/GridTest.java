package com.github.mdsimmo.sudokusolver.tests.components;

import com.github.mdsimmo.sudokusolver.components.Cell;
import com.github.mdsimmo.sudokusolver.components.Grid;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    public static Cell[] makeCells ( int amount ) {
        Cell[] cells = new Cell[amount];
        for ( int i = 0; i < amount; i++ )
            cells[i] = new Cell( amount );
        return cells;
    }

    @Test
    public void testCellOrder() {
        Cell[] cells = makeCells( 9 );
        Cell theSpecial = new Cell( 9 );
        cells[4] = theSpecial;
        Grid grid = new Grid.Row( cells );
        assertEquals( theSpecial, grid.getCell( 5 ) );
    }

    @Test
    public void testGetCell () {
        Grid grid = new Grid.Row( makeCells( 9 ) );
        assertNotNull( grid.getCell( 1 ) );
        assertNotNull( grid.getCell( 9 ) );
        try {
            assertNotNull( grid.getCell( 10 ) );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
        try {
            assertNotNull( grid.getCell( 0 ) );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
    }

    @Test
    public void testIterator () {
        Grid grid = new Grid.Row( makeCells( 9 ) );
        int i = 0;
        for ( Cell cell : grid ) {
            i++;
            assertNotNull( cell );
        }
        assertEquals( 9, i );
    }

    @Test
    public void testSubGrid() {
        Grid.SubGrid grid = new Grid.SubGrid( makeCells( 9 ) );

        assertEquals( grid.getCell( 1 ), grid.getCell( 1, 1 ) );
        assertEquals( grid.getCell( 8 ), grid.getCell( 2, 3 ) );
        assertEquals( grid.getCell( 4 ), grid.getCell( 1, 2 ) );
        assertEquals( grid.getCell( 9 ), grid.getCell( 3, 3 ) );

        try {
            grid.getCell( 1, 4 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
        try {
            grid.getCell( 2, 0 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
        try {
            grid.getCell( 4, 2 );
            fail();
        } catch ( IndexOutOfBoundsException ignored ) {
        }
    }

    @Test
    public void testInvalidSize() {
        try {
            new Grid.Row( makeCells( 5 ) );
            fail();
        } catch ( IllegalArgumentException ignored ) {
        }
    }
}