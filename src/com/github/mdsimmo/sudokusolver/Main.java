package com.github.mdsimmo.sudokusolver;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.solvers.SolverExecutor;

public class Main {

    private static int[][] data = new int[][] {
            { 9,0,0, 0,0,0, 0,0,0 },
            { 0,2,7, 0,6,1, 0,0,9 },
            { 5,0,0, 0,4,0, 0,0,0 },

            { 0,0,0, 8,0,0, 2,0,0 },
            { 0,0,0, 2,0,3, 0,0,0 },
            { 0,0,9, 0,0,0, 6,0,5 },

            { 0,0,0, 0,0,0, 3,0,1 },
            { 0,0,0, 0,0,2, 0,0,4 },
            { 8,7,0, 0,0,0, 0,9,0 },
    };

    public static void main( String[] args ) {

        int size = data.length;
        Board board = new Board( size );
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                int solution = data[j][i];
                if ( solution == 0 )
                    continue;
                board.getCell( i+1, j+1 ).setSolution( solution );
            }
        }

        board.print();

        new SolverExecutor( board ).solve();

        board.print();

    }

}
