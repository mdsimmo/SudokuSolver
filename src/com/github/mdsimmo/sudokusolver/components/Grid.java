package com.github.mdsimmo.sudokusolver.components;

import java.util.Arrays;
import java.util.Iterator;

public abstract class Grid implements Iterable<Cell> {

    public static abstract class Line extends Grid {
        public Line( Cell[] cells ) {
            super( cells );
        }
    }

    public static class Row extends Line {
        public Row( Cell[] cells ) {
            super( cells );
        }
    }

    public static class Column extends Line {
        public Column( Cell[] cells ) {
            super( cells );
        }
    }

    public static class SubGrid extends Grid {
        public SubGrid( Cell[] cells ) {
            super( cells );
        }

        public Cell getCell( int x, int y ) {
            int subSize = subSize();
            if ( x <= 0 || y <= 0 || x > subSize || y > subSize )
                throw new IndexOutOfBoundsException( "Values are off. Passed x=" + x + ", y=" + y );
            return getCell( (y-1) * subSize + x );
        }
    }

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

    private final int size, subSize;
    private final Cell[] cell;

    public Grid( Cell[] cells ) {
        if ( cells == null )
            throw new NullPointerException( "cells cannot be null" );
        this.size = cells.length;
        double sqrt = Math.sqrt( size );
        this.subSize = (int)sqrt;
        if ( sqrt != subSize )
            throw new IllegalArgumentException( "cells must have a square length" );
        if ( size == 0 )
            throw new IllegalArgumentException( "cells must have size greater than 0" );
        this.cell = new Cell[cells.length];
        for ( int i = 0; i < cells.length; i++ ) {
            Cell cell = cells[i];
            if ( cell == null )
                throw new NullPointerException( "null cell at position " + i );
            this.cell[i] = cell;
        }
    }

    public int size() {
        return size;
    }

    public int subSize() {
        return subSize;
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