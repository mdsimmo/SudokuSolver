package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;
import com.github.mdsimmo.sudokusolver.components.Cell;
import com.github.mdsimmo.sudokusolver.components.Grid;
import com.github.mdsimmo.sudokusolver.utils.SubsetFinder;

import java.util.ArrayList;

public class GroupFinder implements Solver {

    @Override
    public boolean apply( Board board ) {
        boolean foundInfo = false;
        final int size = board.size();
        for ( int i = 1; i <= size; i++ ) {
            foundInfo |= apply( board.getColumn( i ) );
            foundInfo |= apply( board.getRow( i ) );
            foundInfo |= apply( board.getSubGrid( i ) );
        }
        return foundInfo;
    }

    boolean apply( Grid grid ) {
        final int size = grid.size();
        int[] numbers = new int[size];
        for ( int i = 0; i < size; i++ )
            numbers[i] = i+1;
        final boolean[] foundInfo = new boolean[] { false };
        for ( int subSetSize = 2; subSetSize <= size-1; subSetSize++ )
            SubsetFinder.findSubsets( numbers, subSetSize, subSet -> foundInfo[0] |= findGroupOf( grid, subSet ) );
        return foundInfo[0];
    }

    boolean findGroupOf( Grid grid, int[] values ) {
        final int size = grid.size();
        ArrayList<Cell> matches = new ArrayList<>();
        outerLoop:
        for ( Cell cell : grid ) {
            for ( int i = 1; i <= size; i++ ) {
                if ( cell.isPossible( i ) ) {
                    if ( !arrayContains( values, i ) )
                        continue outerLoop;
                }
            }
            matches.add( cell );
        }
        if ( values.length < matches.size() )
            throw new RuntimeException( "found more matches than values to fit in them" );
        boolean foundInfo = false;
        if ( values.length == matches.size() ) {
            for ( Cell cell : grid ) {
                if ( matches.contains( cell ) )
                    continue;
                for ( int i = 1; i <= size; i++ ) {
                    if ( arrayContains( values, i ) )
                        foundInfo |= cell.setImpossible( i );
                }
            }
        }
        return foundInfo;
    }

    private static boolean arrayContains( int[] array, int value ) {
        for ( int i : array ) {
            if ( i == value )
                return true;
        }
        return false;
    }
}
