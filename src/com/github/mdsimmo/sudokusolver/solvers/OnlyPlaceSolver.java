package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Grid;

public class OnlyPlaceSolver implements Solver {

    @Override
    public boolean apply( Board board ) {
        final int size = board.size();
        boolean foundInfo = false;
        for ( int i = 1; i <= size; i++ ) {
            foundInfo |= apply( board.getSubGrid( i ) );
            foundInfo |= apply( board.getColumn( i ) );
            foundInfo |= apply( board.getRow( i ) );
        }
        return foundInfo;
    }

    public boolean apply( Grid grid ) {
        final int size = grid.size();
        boolean foundInfo = false;
        for ( int n = 1; n <= size; n++ )
            foundInfo |= apply( grid, n );
        return foundInfo;
    }

    public boolean apply( Grid grid, int number ) {
        final int size = grid.size();
        int found = -1;
        for ( int i = 1; i <= size; i++ ) {
            if ( grid.getCell( i ).isPossible( number ) ) {
                if ( found != -1 ) {
                    // more than two possible options
                    return false;
                } else {
                    // this may be the only possible place
                    found = i;
                }
            }
        }
        if ( found == -1 )
            throw new RuntimeException( "no possible place was found for number: " + number );
        return grid.getCell( found ).setSolution( number );
    }
}
