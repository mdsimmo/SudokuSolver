package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;

/**
 * Goes through all cells and tells all cells in vertical/horizontal alignment that the value of the first cell is an
 * impossible solution
 */
public class RowEliminator implements Solver {

    @Override
    public boolean apply ( Board board ) {
        int size = board.getSize();
        boolean found = false;
        for ( int i = 1; i <= size; i++ ) {
            for ( int j = 1; j <= size; j++ ) {
                Cell cell = board.getCell( i, j );
                if ( !cell.isSolved() )
                    continue;
                int value = cell.getSolution();
                for ( int k = 1; k <= size; k++ ) {
                    if ( k != i )
                        found |= board.getCell( k, j ).setImpossible( value );
                    if ( k != j )
                        found |= board.getCell( i, k ).setImpossible( value );
                }
            }
        }
        return found;
    }
}
