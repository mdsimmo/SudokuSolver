package com.github.mdsimmo.sudokusolver;

import com.github.mdsimmo.sudokusolver.components.Board;

public class SudokuGrids {

    public static int[][] hard1 = new int[][] {
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

    // this one has multiple solutions
    public static int[][] hard2 = new int[][] {
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

    public static int[][] hard3 = new int[][] {
            { 0,2,0, 0,7,0, 0,0,0 },
            { 0,0,0, 0,0,3, 0,0,9 },
            { 6,0,0, 8,0,0, 1,0,0 },

            { 0,0,9, 0,0,0, 7,0,0 },
            { 0,5,0, 0,0,0, 0,6,0 },
            { 0,0,4, 0,0,0, 8,0,0 },

            { 0,0,3, 0,0,9, 0,0,4 },
            { 8,0,0, 5,0,0, 0,0,0 },
            { 0,0,0, 0,6,0, 0,2,0 },
    };

    public static int[][] easy1 = new int[][] {
            { 0,9,3, 1,0,5, 6,4,0 },
            { 7,0,0, 0,0,0, 0,0,5 },
            { 5,0,1, 2,0,9, 3,0,7 },

            { 2,0,0, 0,0,0, 0,0,3 },
            { 0,3,6, 9,0,7, 5,2,0 },
            { 9,0,0, 0,0,0, 0,0,1 },

            { 3,0,2, 4,0,8, 1,0,9 },
            { 6,0,0, 0,0,0, 0,0,4 },
            { 0,4,7, 3,0,2, 8,5,0 },
    };

    public static int[][] medium1 = new int[][] {
            { 0,0,6, 2,4,0, 0,3,0 },
            { 0,3,0, 0,0,0, 0,9,0 },
            { 2,0,0, 0,0,0, 0,7,0 },

            { 5,0,0, 8,0,0, 0,2,0 },
            { 0,0,1, 0,0,0, 6,0,0 },
            { 0,2,0, 0,0,3, 0,0,7 },

            { 0,5,0, 0,0,0, 0,0,3 },
            { 0,9,0, 0,0,0, 0,8,0 },
            { 0,1,0, 0,6,2, 5,0,0 },
    };

    public static int[][] extreme1 = new int[][] {
            { 6,0,5, 4,0,8, 0,0,2 },
            { 0,0,0, 0,7,0, 0,0,1 },
            { 0,1,0, 0,0,0, 0,8,0 },

            { 9,0,8, 2,0,5, 0,0,4 },
            { 0,0,0, 0,0,0, 0,0,0 },
            { 1,0,0, 7,0,3, 8,0,5 },

            { 0,8,0, 0,0,0, 0,2,0 },
            { 5,0,0, 0,2,0, 0,0,0 },
            { 7,0,0, 8,0,1, 6,0,9 },
    };

    public static int[][] fourStars1 = new int[][] {
            { 8,0,9, 0,0,0, 3,0,0 },
            { 0,0,0, 0,7,0, 0,0,9 },
            { 0,5,0, 3,9,0, 0,2,0 },

            { 7,4,0, 0,0,0, 8,0,5 },
            { 0,0,0, 4,0,2, 0,0,0 },
            { 9,0,1, 0,0,0, 0,3,4 },

            { 0,2,0, 0,5,3, 0,7,0 },
            { 5,0,0, 0,2,0, 0,0,0 },
            { 0,0,7, 0,0,0, 1,0,2 },
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
