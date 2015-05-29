package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Grid;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridEliminatorTest {

    @Test
    public void testApplyGrid() throws Exception {
        Board board = new Board( 4 );
        board.getCell( 1, 1 ).setSolution( 1 );
        board.getCell( 1, 2 ).setSolution( 2 );
        board.getCell( 2, 1 ).setSolution( 3 );

        assertTrue( new GridEliminator().apply( board ) );

        assertEquals( 4, board.getCell( 2, 2 ).getSolution() );
    }

    @Test
    public void testApplyColumn() throws Exception {
        Board board = new Board( 4 );
        board.getCell( 3, 1 ).setSolution( 1 );
        board.getCell( 3, 2 ).setSolution( 2 );
        board.getCell( 3, 4 ).setSolution( 3 );

        assertTrue( new GridEliminator().apply( board ) );

        assertEquals( 4, board.getCell( 3, 3 ).getSolution() );

    }

    @Test
    public void testApplyRow() throws Exception {
        Board board = new Board( 4 );
        board.getCell( 1, 3 ).setSolution( 1 );
        board.getCell( 2, 3 ).setSolution( 2 );
        board.getCell( 3, 3 ).setSolution( 3 );

        assertTrue( new GridEliminator().apply( board ) );

        assertEquals( 4, board.getCell( 4, 3 ).getSolution() );

    }

    @Test
    public void testNotClaimingToSolve() {
        Board board = new Board( 4 );
        Solver solver = new GridEliminator();

        assertFalse( new GridEliminator().apply( board ) );

        board.getCell( 1, 1 ).setSolution( 1 );

        assertTrue( solver.apply( board ) );
        assertFalse( solver.apply( board ) );
    }
    @Test
    public void testEliminateAll() {
        Board board = new Board( 4 );
        Grid grid = board.getSubGrid( 2 );

        assertTrue( new GridEliminator().eliminateAllFrom( grid, 1 ) );

        assertFalse( grid.getCell( 1 ).isPossible( 1 ) );
        assertFalse( grid.getCell( 2 ).isPossible( 1 ) );
        assertFalse( grid.getCell( 3 ).isPossible( 1 ) );
        assertFalse( grid.getCell( 4 ).isPossible( 1 ) );

        assertTrue( grid.getCell( 2 ).isPossible( 2 ) );
        assertTrue( grid.getCell( 2 ).isPossible( 3 ) );
        assertTrue( grid.getCell( 2 ).isPossible( 4 ) );
    }

    @Test
    public void testEliminatedAround() {
        Board board = new Board( 4 );
        board.getCell( 3, 3 ).setSolution( 1 );
        new GridEliminator().apply( board );

        assertFalse( board.getCell( 4, 4 ).isPossible( 1 ) );
        assertFalse( board.getCell( 1, 3 ).isPossible( 1 ) );
        assertFalse( board.getCell( 3, 1 ).isPossible( 1 ) );
    }
}