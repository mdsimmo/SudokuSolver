package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Grid;

public class LineEliminator implements Solver {

    @Override
    public boolean apply( Board board ) {
        boolean foundInfo = false;
        final int subSize = board.subSize();
        for ( int i = 1; i <= subSize; i++ )
            for ( int j = 1; j <= subSize; j++ )
                foundInfo |= apply( board, board.getSubGrid( i, j ) , i, j );
        return foundInfo;
    }

    public boolean apply( Board board, Grid.SubGrid grid, final int x, final int y ) {
        final int size = grid.size();
        final int subSize = grid.subSize();
        boolean infoFound = false;
        for ( int n = 1; n <= size; n++ ) {
            for ( int i = 1; i <= subSize; i++ ) {
                if ( onlyInColumn( grid, i, n ) )
                    infoFound |= setColumnImpossible( board, grid, (x-1)*subSize+i, n );
                if ( onlyInRow( grid, i, n ) )
                    infoFound |= setRowImpossible( board, grid, (y - 1) * subSize + i, n );
            }
        }
        return infoFound;
    }

    public boolean setColumnImpossible( Board board, Grid.SubGrid origin, int x, int number ) {
        int size = board.size();
        boolean infoFound = false;
        for ( int i = 1; i <= size; i++ ) {
            if ( board.getSubGridContaining( x, i ) == origin )
                continue;
            infoFound |= board.getCell( x, i ).setImpossible( number );
        }
        return infoFound;
    }

    public boolean setRowImpossible( Board board, Grid.SubGrid origin, int y, int number ) {
        int size = board.size();
        boolean infoFound = false;
        for ( int i = 1; i <= size; i++ ) {
            if ( board.getSubGridContaining( i, y ) == origin )
                continue;
            infoFound |= board.getCell( i, y ).setImpossible( number );
        }
        return infoFound;
    }

    public boolean onlyInColumn( Grid.SubGrid grid, int x, int number ) {
        final int subSize = grid.subSize();
        OuterLoop:
        for ( int i = 1; i <= subSize; i++ ) {
            if ( i == x ) {
                // check that the number is actually contained in the row
                for ( int j = 1; j <= subSize; j++ ) {
                    if ( grid.getCell( i, j ).isPossible( number ) )
                        continue OuterLoop;
                }
                return false;
            } else {
                // if the number is found, then the x row was not the only one
                for ( int j = 1; j <= subSize; j++ ) {
                    if ( grid.getCell( i, j ).isPossible( number ) ) {
                        return false;
                    }
                }
            }
        }
        // not found anywhere except the asked column
        return true;
    }

    public boolean onlyInRow( Grid.SubGrid grid, int y, int number ) {
        final int subSize = grid.subSize();
        OuterLoop:
        for ( int i = 1; i <= subSize; i++ ) {
            if ( i == y ) {
                // check that the number is actually contained in the row
                for ( int j = 1; j <= subSize; j++ ) {
                    if ( grid.getCell( j, i ).isPossible( number ) )
                        continue OuterLoop;
                }
                return false;
            } else {
                // if the number is found, then the x row was not the only one
                for ( int j = 1; j <= subSize; j++ ) {
                    if ( grid.getCell( j, i ).isPossible( number ) ) {
                        return false;
                    }
                }
            }
        }
        // not found anywhere except the asked column
        return true;
    }

}
