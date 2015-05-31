package com.github.mdsimmo.sudokusolver.utils;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetFinderTest extends TestCase {

    public void testFindSubset() throws Exception {
        int[] set = new int[] { 1, 2, 3, 4 };
        ArrayList<int[]> sets = new ArrayList<>( 4 );
        SubsetFinder.findSubsets( set, 3, subSet -> sets.add( Arrays.copyOf( subSet, subSet.length ) ) );
        assertEquals( 4, set.length );
        assertTrue( contains( sets, new int[]{ 1, 2, 3 } ) );
        assertTrue( contains( sets, new int[]{ 1, 2, 4 } ) );
        assertTrue( contains( sets, new int[]{ 1, 3, 4 } ) );
        assertTrue( contains( sets, new int[]{ 2, 3, 4 } ) );
    }

    private boolean contains( List<int[]> list, int[] test) {
        for ( int[] a : list )
            if ( Arrays.equals( a, test ) )
                return true;
        return false;
    }
}