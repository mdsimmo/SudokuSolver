package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.SudokuGrids;
import com.github.mdsimmo.sudokusolver.components.Board;
import junit.framework.TestCase;

public class OnlyGridInLineTest extends TestCase {

    int[][] setup1 = new int[][] {
            { 0,0,  0,0 },
            { 2,0,  0,0 },

            { 0,3,  0,0 },
            { 0,0,  0,0 },
    };

    int[][] setup2 = new int[][] {
            { 0,0,  0,0 },
            { 0,0,  0,0 },

            { 0,0,  3,0 },
            { 4,0,  0,0 },
    };

    public void testApplyRow() throws Exception {
        Board board = SudokuGrids.load( setup1 );
        // manually fill in the work of other solvers
        board.getCell( 2, 2 ).setImpossible( 3 );

        Solver solver = new OnlyGridInLine();
        assertTrue( solver.apply( board ) );

        // it should find these changes
        assertFalse( board.getCell( 3, 1 ).isPossible( 3 ) );
        assertFalse( board.getCell( 4, 1 ).isPossible( 3 ) );

        // shouldn't affect these
        assertTrue( board.getCell( 3, 2 ).isPossible( 3 ) );
        assertTrue( board.getCell( 4, 2 ).isPossible( 3 ) );
    }

    public void testApplyCol() throws Exception {
        Board board = SudokuGrids.load( setup2 );
        // manually fill in the work of other solvers
        board.getCell( 3, 4 ).setImpossible( 4 );

        Solver solver = new OnlyGridInLine();
        assertTrue( solver.apply( board ) );

        // it should find these changes
        assertFalse( board.getCell( 4, 1 ).isPossible( 4 ) );
        assertFalse( board.getCell( 4, 2 ).isPossible( 4 ) );

        // shouldn't affect these
        assertTrue( board.getCell( 3, 1 ).isPossible( 4 ) );
        assertTrue( board.getCell( 3, 2 ).isPossible( 4 ) );
    }
}