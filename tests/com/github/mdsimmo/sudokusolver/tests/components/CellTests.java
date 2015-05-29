package com.github.mdsimmo.sudokusolver.tests.components;

import com.github.mdsimmo.sudokusolver.components.Cell;
import junit.framework.TestCase;

public class CellTests extends TestCase {

    public void testSetUp() {
        Cell cell = new Cell( 3 );
        assertTrue( cell.isPossible( 1 ) );
        assertTrue( cell.isPossible( 2 ) );
        assertTrue( cell.isPossible( 3 ) );
    }

    public void testSetSolution() {
        Cell cell = new Cell( 3 );
        assertFalse( cell.isSolved() );
        try {
            cell.getSolution();
            fail();
        } catch (IllegalStateException e) {
            // valid pass
        }
        cell.setSolution( 2 );
        int solution = cell.getSolution();
        assertEquals( 2, solution );
        assertTrue( cell.isSolved() );
    }

    public void testSetImpossible() {
        Cell cell = new Cell( 3 );
        assertTrue( cell.isPossible( 2 ) );
        cell.setImpossible( 2 );
        assertFalse( cell.isPossible( 2 ) );
    }

    public void testAutoSolve() {
        Cell cell = new Cell( 3 );
        cell.setImpossible( 1 );
        cell.setImpossible( 2 );
        try {
            cell.setImpossible( 3 );
            fail();
        } catch ( IllegalStateException e ) {
            // good
        }
        assertEquals( 3, cell.getSolution() );
    }

    public void testNewKnowledge() {
        Cell cell = new Cell( 3 );
        assertTrue( cell.setImpossible( 1 ) );
        assertTrue( cell.setImpossible( 2 ) );
        assertFalse( cell.setImpossible( 2 ) );
        assertFalse( cell.setSolution( 3 ) );

        cell = new Cell( 3 );
        assertTrue( cell.setSolution( 3 ) );
        assertFalse( cell.setImpossible( 2 ) );
    }


}