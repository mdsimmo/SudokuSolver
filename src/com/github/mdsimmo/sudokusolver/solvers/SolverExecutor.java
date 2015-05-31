package com.github.mdsimmo.sudokusolver.solvers;

import com.github.mdsimmo.sudokusolver.components.Board;

public class SolverExecutor {

    private static final Solver[] solvers = new Solver[] {
        new GridEliminator(), new OnlyPlaceSolver(), new LineEliminator(),
            new OnlyGridInLine(), new GroupFinder()
    };

    private final Board board;

    public SolverExecutor( Board board ) {
        this.board = board;
    }

    public void solve() {
        while ( true ) {
            boolean foundNew = false;
            for ( Solver solver : solvers ) {
                if ( solver.apply( board ) ) {
                    foundNew = true;
                    break;
                }
            }
            if ( !foundNew )
                break;
        }
    }

}
