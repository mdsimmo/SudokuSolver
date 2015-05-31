package com.github.mdsimmo.sudokusolver;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.solvers.SolverExecutor;

public class Main {

    public static void main( String[] args ) {

        Board board = SudokuGrids.load( SudokuGrids.fourStars1 );

        //board.getCell( 2, 2 ).setSolution( 1 );
        board.print( false );

        new SolverExecutor( board ).solve();

        board.print( true );

    }

}
