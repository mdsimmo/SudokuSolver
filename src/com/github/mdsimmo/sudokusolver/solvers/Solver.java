package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;

public interface Solver {

    /**
     * Tries to determine something new about a board.
     * @param board the board to look at
     * @return true if something new was found
     */
    public boolean apply( Board board );

}
