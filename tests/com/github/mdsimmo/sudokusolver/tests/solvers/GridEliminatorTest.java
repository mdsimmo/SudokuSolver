package com.github.mdsimmo.sudokusolver.tests.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.solvers.GridEliminator;
import com.github.mdsimmo.sudokusolver.solvers.RowEliminator;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridEliminatorTest {

    @Test
    public void testApply() throws Exception {
        Board board = new Board( 4 );
        board.getCell( 1, 1 ).setSolution( 1 );
        board.getCell( 1, 2 ).setSolution( 2 );
        board.getCell( 2, 1 ).setSolution( 3 );

        board.getCell( 4, 4 ).setSolution( 4 );

        new GridEliminator().apply( board );

        assertEquals( 4, board.getCell( 2, 2 ).getSolution() );
        assertFalse( board.getCell( 3, 3 ).isPossible( 4 ) );
    }
}