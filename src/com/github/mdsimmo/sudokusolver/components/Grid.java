package com.github.mdsimmo.sudokusolver.components;

import java.util.Arrays;
import java.util.Iterator;

public class Grid implements Iterable<Cell> {

    private class GridIterator implements Iterator<Cell> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < cell.length;
        }

        @Override
        public Cell next() {
            return cell[index++];
        }
    }

    private final Cell[] cell;

    public Grid( Cell[] cells ) {
        if ( cells == null )
            throw new NullPointerException( "cells cannot be null" );
        this.cell = new Cell[cells.length];
        for ( int i = 0; i < cells.length; i++ ) {
            Cell cell = cells[i];
            if ( cell == null )
                throw new NullPointerException( "null cell at position " + i );
            this.cell[i] = cell;
        }
    }

    public Cell getCell( int n ) {
        return cell[n-1];
    }

    @Override
    public Iterator<Cell> iterator() {
        return new GridIterator();
    }

    @Override
    public String toString () {
        return Arrays.toString( cell );
    }
}