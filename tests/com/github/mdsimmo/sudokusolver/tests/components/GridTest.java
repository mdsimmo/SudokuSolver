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
        Grid grid = new Grid( cells );
        assertEquals( theSpecial, grid.getCell( 5 ) );
    }

    @Test
    public void testGetCell () {
        Grid grid = new Grid( makeCells( 9 ) );
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
        Grid grid = new Grid( makeCells( 9 ) );
        int i = 0;
        for ( Cell cell : grid ) {
            i++;
            assertNotNull( cell );
        }
        assertEquals( 9, i );
    }
}