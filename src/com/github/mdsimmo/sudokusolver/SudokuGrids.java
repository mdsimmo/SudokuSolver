package com.github.mdsimmo.sudokusolver;

import com.github.mdsimmo.sudokusolver.components.Board;

public class SudokuGrids {

    public static int[][] grid1 = new int[][] {
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

    public static int[][] grid2 = new int[][] {
            { 4,0,0, 0,3,0, 0,9,0 },
            { 2,0,0, 0,1,0, 0,0,0 },
            { 8,0,0, 0,0,5, 4,0,0 },

            { 0,0,6, 0,0,2, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,3, 7,0,0, 8,1,0 },

            { 0,0,0, 8,5,0, 0,3,0 },
            { 0,0,0, 0,0,0, 0,0,5 },
            { 7,4,0, 0,6,0, 0,0,1 },
    };

    public static int[][] blank = new int[][] {
            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },

            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },

            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },
            { 0,0,0, 0,0,0, 0,0,0 },
    };

    public static Board load( int[][] data ) {
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
        return board;
    }

}
