package com.github.mdsimmo.sudokusolver.components;

import java.util.Arrays;

public class Cell {

    private static final int NOT_SOLVED = -1;
    private final int maxValue;
    private final boolean[] possible;
    private int solution = NOT_SOLVED;

    /**
     * Constructs a cell that can have values between 1 to maxValue (both inclusive)
     * @param maxValue the max value that this cell could have
     */
    public Cell( int maxValue ) {
        if ( maxValue <= 0 )
            throw new IllegalArgumentException( "maxValue cannot be <= 0. Passed " + maxValue );
        this.maxValue = maxValue;
        possible = new boolean[maxValue];
        Arrays.fill( possible, true );
    }

    /**
     * Sets that this cell has been solved. This will also automatically remove all other possibles solutions.
     * @param value the solution
     * @return true if this was a previously unknown solution
     * @throws IllegalStateException if the solution was not possible
     */
    public boolean setSolution( int value ) {
        checkRange( value );
        if ( !isPossible( value ) )
            throw new IllegalStateException( "value has previously set to be impossible. Passed " + value);
        if ( isSolved() )
            return false;
        Arrays.fill( possible, false );
        possible[value-1] = true;
        solution = value;
        return true;
    }

    /**
     * Tests if the cell has been solved
     * @return true if solved
     */
    public boolean isSolved() {
        return solution != NOT_SOLVED;
    }

    /**
     * Gets what the cell has been solved to. Always check that the cell has been solved (with {@link #isSolved()}
     * first. If the cell is not solved, this method will throw an {@link IllegalStateException}.
     * @return the solved value
     * @throws IllegalStateException if the cell has not been solved
     * @see {@link #isSolved()}
     */
    public int getSolution() {
        if ( !isSolved() )
            throw new IllegalStateException( "The cell has not been solved yet" );
        return solution;
    }

    /**
     * Tests if the value is a possible option for this cell.
     * @param value the value to test for
     * @return true if the value is the possible solution
     */
    public boolean isPossible( int value ) {
        checkRange( value );
        return possible[value-1];
    }

    /**
     * Sets that the value is impossible to be the solution
     * @param value the impossible solution
     * @return true if new knowledge was added
     * @throws IllegalStateException if the value was a previously set solution
     */
    public boolean setImpossible( int value ) {
        checkRange( value );
        if ( solution == value )
            throw new IllegalStateException( "The impossible value is the solution! Passed " + value );
        boolean wasPossible = possible[value-1];
        possible[value-1] = false;

        if ( isSolved() )
            return false;

        // try to self solve
        int foundSolution = NOT_SOLVED;
        for ( int i = 1; i <= maxValue; i++ ) {
            if ( possible[i-1] ) {
                if ( foundSolution == NOT_SOLVED ) {
                    foundSolution = i;
                } else {
                    return wasPossible;
                }
            }
        }
        if ( foundSolution == NOT_SOLVED )
            throw new IllegalStateException( "No possible solutions for the cell was found" );
        else
            setSolution( foundSolution );
        return wasPossible;
    }

    private void checkRange( int value ) {
        if ( value <= 0 || value > maxValue )
            throw new IllegalArgumentException( "value cannot be <= 0 or > " + maxValue + ". Passed " + value );
    }

    @Override
    public String toString() {
        if ( isSolved() )
            return " " + solution + " ";
        else
            return " - ";
    }

    public String toStringVerbose() {
        StringBuilder answer = new StringBuilder( 11 );
        answer.append( ' ' );
        for ( int i = 1; i <= possible.length; i++ ) {
            answer.append( isPossible( i ) ?  Integer.toString( i ) : "." );
        }
        answer.append( ' ' );
        return answer.toString();
    }
}
