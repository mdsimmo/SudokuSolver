package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;
import com.github.mdsimmo.sudokusolver.components.Grid;

public class OnlyGridInLine implements Solver {

    @Override
    public boolean apply( Board board ) {
        boolean infoFound = false;
        int size = board.size();
        for ( int i = 1; i <= size; i++ ) {
            for ( int n = 1; n <= size; n++ ) {
                infoFound |= applyGridInRow( board, i, n );
                infoFound |= applyGridInCol( board, i, n );
            }
        }
        return infoFound;
    }

    boolean applyGridInRow( Board board, int y, int number ) {
        int size = board.size();
        Grid.SubGrid onlyIn = null;
        for ( int i = 1; i <= size; i++ ) {
            Cell cell = board.getCell( i, y );
            if ( cell.isPossible( number ) ) {
                Grid.SubGrid grid = board.getSubGridContaining( i, y );
                if ( onlyIn == null || onlyIn == grid )
                    onlyIn = grid;
                else
                    return false;
            }
        }
        if ( onlyIn == null )
            return false;

        boolean infoFound = false;
        final int subSize = board.subSize();
        for ( int i = 1; i <= subSize; i++ ) {
            for ( int j = 1; j <= subSize; j++ ) {
                if ( (y-1) % subSize + 1 == j )
                    continue;
                Cell cell = onlyIn.getCell( i, j );
                infoFound |= cell.setImpossible( number );
            }
        }
        return infoFound;
    }

    boolean applyGridInCol( Board board, int x, int number ) {
        int size = board.size();
        Grid.SubGrid onlyIn = null;
        for ( int j = 1; j <= size; j++ ) {
            Cell cell = board.getCell( x, j );
            if ( cell.isPossible( number ) ) {
                Grid.SubGrid grid = board.getSubGridContaining( x, j );
                if ( onlyIn == null || onlyIn == grid )
                    onlyIn = grid;
                else
                    return false;
            }
        }
        if ( onlyIn == null )
            return false;

        boolean infoFound = false;
        final int subSize = board.subSize();
        for ( int i = 1; i <= subSize; i++ ) {
            for ( int j = 1; j <= subSize; j++ ) {
                if ( (x-1) % subSize + 1 == i )
                    continue;
                Cell cell = onlyIn.getCell( i, j );
                infoFound |= cell.setImpossible( number );
            }
        }
        return infoFound;
    }

}
