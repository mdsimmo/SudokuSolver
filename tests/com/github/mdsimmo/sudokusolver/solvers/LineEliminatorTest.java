package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import junit.framework.TestCase;

public class LineEliminatorTest extends TestCase {

    public void testApplyCol() {
        Board board = new Board( 4 );
        board.getCell( 1, 1 ).setSolution( 1 );
        board.getCell( 1, 2 ).setSolution( 2 );

        Solver solver = new LineEliminator();
        assertTrue( solver.apply( board ) );

        // should detect that 3,4 must block column 2
        assertFalse( board.getCell( 2, 3 ).isPossible( 3 ) );
        assertFalse( board.getCell( 2, 3 ).isPossible( 4 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 3 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 4 ) );

        // shouldn't touch top 2 squares
        assertTrue( board.getCell( 2, 1 ).isPossible( 3 ) );
        assertTrue( board.getCell( 2, 1 ).isPossible( 4 ) );
    }

    public void testApplyRow() {
        Board board = new Board( 4 );
        board.getCell( 3, 3 ).setSolution( 1 );
        board.getCell( 4, 3 ).setSolution( 2 );

        Solver solver = new LineEliminator();
        assertTrue( solver.apply( board ) );

        assertFalse( board.getCell( 1, 4 ).isPossible( 3 ) );
        assertFalse( board.getCell( 1, 4 ).isPossible( 4 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 3 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 4 ) );

        // shouldn't touch top 2 squares
        assertTrue( board.getCell( 3, 4 ).isPossible( 3 ) );
        assertTrue( board.getCell( 4, 4 ).isPossible( 4 ) );
    }

    public void testOnlyInCol() {
        Board board = new Board( 4 );
        LineEliminator solver = new LineEliminator();

        assertFalse( solver.onlyInColumn( board.getSubGrid( 1 ), 2, 1 ) );

        board.getCell( 1, 1 ).setImpossible( 1 );

        assertFalse( solver.onlyInColumn( board.getSubGrid( 1 ), 2, 1 ) );

        board.getCell( 1, 2 ).setImpossible( 1 );

        assertTrue( solver.onlyInColumn( board.getSubGrid( 1 ), 2, 1 ) );
    }

    public void testOnlyInRow() {
        Board board = new Board( 4 );
        LineEliminator solver = new LineEliminator();

        assertFalse( solver.onlyInRow( board.getSubGrid( 1 ), 2, 1 ) );

        board.getCell( 1, 1 ).setImpossible( 1 );

        assertFalse( solver.onlyInRow( board.getSubGrid( 1 ), 2, 1 ) );

        board.getCell( 2, 1 ).setImpossible( 1 );

        assertTrue( solver.onlyInRow( board.getSubGrid( 1 ), 2, 1 ) );
    }

    public void testSubApplyCol() {
        Board board = new Board( 4 );
        board.getCell( 1, 1 ).setImpossible( 1 );
        board.getCell( 1, 2 ).setImpossible( 1 );

        LineEliminator eliminator = new LineEliminator();
        assertTrue( eliminator.apply( board, board.getSubGrid( 1 ), 1, 1 ) );

        assertFalse( board.getCell( 2, 3 ).isPossible( 1 ) );
        assertFalse( board.getCell( 2, 4 ).isPossible( 1 ) );

        assertTrue( board.getCell( 2, 1 ).isPossible( 1 ) );
        assertTrue( board.getCell( 2, 2 ).isPossible( 1 ) );
    }

    public void testSubApplyRow() {
        Board board = new Board( 4 );
        board.getCell( 1, 1 ).setImpossible( 1 );
        board.getCell( 2, 1 ).setImpossible( 1 );

        LineEliminator eliminator = new LineEliminator();
        assertTrue( eliminator.apply( board, board.getSubGrid( 1 ), 1, 1 ) );

        assertFalse( board.getCell( 3, 2 ).isPossible( 1 ) );
        assertFalse( board.getCell( 4, 2 ).isPossible( 1 ) );

        assertTrue( board.getCell( 1, 2 ).isPossible( 1 ) );
        assertTrue( board.getCell( 2, 2 ).isPossible( 1 ) );
    }

}