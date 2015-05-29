package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;
import com.github.mdsimmo.sudokusolver.components.Grid;

public class GridEliminator implements Solver {

    @Override
    public boolean apply ( Board board ) {
        boolean infoFound = false;
        final int size = board.size();
        for ( int i = 1; i <= size; i++ ) {
            infoFound |= apply( board.getSubGrid( i ) );
            infoFound |= apply( board.getRow( i ) );
            infoFound |= apply( board.getColumn( i ) );
        }
        return infoFound;
    }

    public boolean apply( Grid grid ) {
        final int size = grid.size();
        boolean foundInfo = false;
        for ( int i = 1; i <= size; i++ ) {
            Cell cell = grid.getCell( i );
            if ( cell.isSolved() )
                foundInfo |= eliminateAllFrom( grid, cell.getSolution() );
        }
        return foundInfo;
    }

    public boolean eliminateAllFrom( Grid grid, int number ) {
        final int size = grid.size();
        boolean foundInfo = false;
        for ( int i = 1; i <= size; i++ ) {
            Cell cell = grid.getCell( i );
            if ( cell.isSolved() )
                continue;
            foundInfo |= cell.setImpossible( number );
        }
        return foundInfo;
    }
}
