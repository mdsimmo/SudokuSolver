package com.github.mdsimmo.sudokusolver;

import com.github.mdsimmo.sudokusolver.components.Board;
import junit.framework.TestCase;

public class SudokuGridsTest extends TestCase {

    public void testLoad() throws Exception {
        int[][] data = new int[][] {
                { 1,2,  0,0 },
                { 3,4,  0,0 },

                { 2,1,  4,3 },
                { 0,0,  2,1 },
        };
        Board board = SudokuGrids.load( data );

        assertEquals( 4, board.size() );

        assertEquals( 1, board.getCell( 1, 1 ).getSolution() );
        assertEquals( 3, board.getCell( 1, 2 ).getSolution() );
        assertEquals( 3, board.getCell( 4, 3 ).getSolution() );
        assertEquals( 2, board.getCell( 3, 4 ).getSolution() );

        assertFalse( board.getCell( 3, 2 ).isSolved() );
        assertFalse( board.getCell( 4, 1 ).isSolved() );
        assertFalse( board.getCell( 1, 4 ).isSolved() );
    }

    public void testNullPointer() {
        try {
            SudokuGrids.load( null );
            fail();
        } catch ( NullPointerException ignored ) {
        }
    }
}