package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;
import com.github.mdsimmo.sudokusolver.components.Grid;
import com.github.mdsimmo.sudokusolver.components.GridTest;
import junit.framework.TestCase;

public class OnlyPlaceSolverTest extends TestCase {

    public void testGuidedSolveGrid() {
        Cell[] cells = GridTest.makeCells( 4 );
        for ( int i = 1; i < 4; i++ )
            cells[i].setImpossible( 1 );
        // cells[0] is now only possible place for a 1
        Grid grid = new Grid.Row( cells );
        OnlyPlaceSolver solver = new OnlyPlaceSolver();
        assertTrue( solver.apply( grid ) );
        assertEquals( 1, cells[0].getSolution() );
    }

    public void testSelfSolve() {
        Board board = new Board( 4 );
        board.getCell( 3, 3 ).setImpossible( 1 );
        board.getCell( 3, 4 ).setImpossible( 1 );
        board.getCell( 4, 3 ).setImpossible( 1 );

        Solver solver = new OnlyPlaceSolver();
        assertTrue( solver.apply( board ) );

        assertEquals( 1, board.getCell( 4, 4 ).getSolution() );
    }

    public void testClaimingToSolve() {
        Board board = new Board( 4 );
        board.getCell( 3, 3 ).setImpossible( 1 );
        board.getCell( 3, 4 ).setImpossible( 1 );
        board.getCell( 4, 3 ).setImpossible( 1 );

        Solver solver = new OnlyPlaceSolver();
        assertTrue( solver.apply( board ) );
        assertFalse( solver.apply( board ) );
    }
}