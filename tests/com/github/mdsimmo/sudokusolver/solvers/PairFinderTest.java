package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.SudokuGrids;
import com.github.mdsimmo.sudokusolver.components.Board;
import junit.framework.TestCase;

public class PairFinderTest extends TestCase {

    private int[][] setup1 = new int[][] {
            { 0,0,  0,0 },
            { 0,0,  0,0 },

            { 0,0,  0,0 },
            { 0,0,  0,0 },
    };

    public void testApply() {
        Board board = SudokuGrids.load( setup1 );
        board.getCell( 1, 1 ).setImpossible( 1 );
        board.getCell( 1, 1 ).setImpossible( 2 );
        board.getCell( 1, 2 ).setImpossible( 1 );
        board.getCell( 1, 2 ).setImpossible( 2 );

        Solver solver = new GroupFinder();
        assertTrue( solver.apply( board ) );
        // it may take a few shots to find everything
        solver.apply( board );
        solver.apply( board );
        solver.apply( board );
        solver.apply( board );

        // find the ones in the same sub grid
        assertFalse( board.getCell( 2, 1 ).isPossible( 3 ) );
        assertFalse( board.getCell( 2, 1 ).isPossible( 4 ) );
        assertFalse( board.getCell( 2, 2 ).isPossible( 3 ) );
        assertFalse( board.getCell( 2, 2 ).isPossible( 4 ) );

        // find the ones in the same column
        assertFalse( board.getCell( 1, 3 ).isPossible( 3 ) );
        assertFalse( board.getCell( 1, 3 ).isPossible( 4 ) );
        assertFalse( board.getCell( 1, 4 ).isPossible( 3 ) );
        assertFalse( board.getCell( 1, 4 ).isPossible( 4 ) );
        assertFalse( board.getCell( 2, 3 ).isPossible( 1 ) );
        assertFalse( board.getCell( 2, 3 ).isPossible( 2 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 1 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 2 ) );

        // should have altered these ones
        assertTrue( board.getCell( 1, 1 ).isPossible( 3 ) );
        assertTrue( board.getCell( 1, 2 ).isPossible( 4 ) );
    }
}