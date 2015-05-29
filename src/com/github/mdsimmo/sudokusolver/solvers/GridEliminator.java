package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;
import com.github.mdsimmo.sudokusolver.components.Grid;

public class GridEliminator implements Solver {

    @Override
    public boolean apply ( Board board ) {
        boolean infoFound = false;

        int size = board.getSize();
        for ( int i = 1; i <= size; i++ ) {
            Grid grid = board.getSubGrid( i );
            for ( int j = 1; j <= size; j++ ) {
                Cell cell = grid.getCell( j );
                if ( !cell.isSolved() )
                    continue;
                int value = cell.getSolution();
                for ( int k = 1; k <= size; k++ ) {
                    if ( k == j )
                        continue;
                    infoFound |= grid.getCell( k ).setImpossible( value );
                }
            }
        }

        return infoFound;
    }
}
