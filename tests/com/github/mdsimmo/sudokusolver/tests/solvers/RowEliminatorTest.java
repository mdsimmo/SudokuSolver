package com.github.mdsimmo.sudokusolver.tests.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.solvers.RowEliminator;
import com.github.mdsimmo.sudokusolver.solvers.SolverExecutor;
import org.junit.Test;

import static org.junit.Assert.*;

public class RowEliminatorTest {

    @Test
    public void testApply() throws Exception {
        Board board = new Board( 4 );
        board.getCell( 1, 1 ).setSolution( 1 );
        board.getCell( 1, 2 ).setSolution( 2 );
        board.getCell( 1, 3 ).setSolution( 3 );

        board.getCell( 2, 2 ).setSolution( 3 );
        board.getCell( 3, 2 ).setSolution( 4 );

        new RowEliminator().apply( board );

        assertEquals( 4, board.getCell( 1, 4 ).getSolution() );
        assertEquals( 1, board.getCell( 4, 2 ).getSolution() );
        assertFalse( board.getCell( 4, 1 ).isPossible( 1 ) );

    }
}