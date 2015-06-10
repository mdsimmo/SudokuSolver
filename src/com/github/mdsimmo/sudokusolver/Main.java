package com.github.mdsimmo.sudokusolver;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.solvers.SolverExecutor;

public class Main {

    public static void main( String[] args ) {

        Board board = SudokuGrids.load( SudokuGrids.medium2 );
        board.getCell( 5, 3 ).setSolution( 2 );
        
        board.print( false );

        new SolverExecutor( board ).solve();

        board.print( true );

    }

}
