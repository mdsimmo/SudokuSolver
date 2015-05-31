package com.github.mdsimmo.sudokusolver.utils;

import java.util.function.Consumer;

public class SubsetFinder {

    public static void findSubsets( final int[] set, final int size, final Consumer<int[]> consumer ) {
        int[] subset = new int[size];
        fillSubset( set, subset, 0, 0, consumer );
    }

    static void fillSubset( int[] set, int[] subset, int currentSize, int smallestIndex, Consumer<int[]> consumer ) {
        if ( currentSize == subset.length ) {
            consumer.accept( subset );
            return;
        }
        for ( int i = smallestIndex; i < set.length; i++ ) {
            subset[currentSize] = set[i];
            fillSubset( set, subset, currentSize+1, i+1, consumer );
        }
    }

}
