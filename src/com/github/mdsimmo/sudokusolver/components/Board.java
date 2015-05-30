package com.github.mdsimmo.sudokusolver.components;

import java.util.Iterator;

public class Board  implements Iterable<Cell> {

    private class BoardIterator implements Iterator<Cell> {
        private int index = 0;

        @Override
        public boolean hasNext () {
            return index < cells.length*cells.length;
        }

        @Override
        public Cell next () {
            int x = index % cells.length;
            int y = index / cells.length;
            Cell cell = cells[x][y];
            index++;
            return cell;
        }
    }

    private final int size, subSize;
    private final Cell[][] cells;
    private final Grid.Row[] rows;
    private final Grid.Column[] cols;
    private final Grid.SubGrid[] subGrid;

    /**
     * Constructs a sudoku grid of the default 9x9 grid
     */
    public Board() {
        this( 9 );
    }

    /**
     * Constructs a sudoku grid. The board will be size x size square, thus, each cell will have size options. The
     * normal sudoku board has a size of 9;
     * @param size the size of the sudoku board
     */
    public Board( int size ) {
        if ( size <= 0 )
            throw new IllegalArgumentException( "size cannot be <= 0" );
        this.subSize = (int)Math.sqrt( size );
        if ( Math.sqrt( size ) != subSize )
            throw new IllegalArgumentException( "size must be a square. Passed " + size );
        this.size = size;

        this.cells = new Cell[size][size];
        rows = new Grid.Row[size];
        cols = new Grid.Column[size];
        subGrid = new Grid.SubGrid[size];

        fillCells();
        fillRowsCols();
        fillSubGrids();
    }

    private void fillCells() {
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                cells[i][j] = new Cell(size);
            }
        }
    }

    private void fillRowsCols() {
        for ( int i = 0; i < size; i++ ) {
            Cell[] rowArray = new Cell[size];
            Cell[] colArray = new Cell[size];
            for ( int j = 0; j < size; j++ ) {
                rowArray[j] = cells[j][i];
                colArray[j] = cells[i][j];
            }
            rows[i] = new Grid.Row( rowArray );
            cols[i] = new Grid.Column( colArray );
        }
    }

    private void fillSubGrids() {
        for ( int i = 0; i < subSize; i++ ) {
            for ( int j = 0; j < subSize; j++ ) {
                Cell[] gridCells = new Cell[size];
                for ( int x = 0; x < subSize; x++ ) {
                    for ( int y = 0; y < subSize; y++ ) {
                        gridCells[y * subSize + x] = cells[i * subSize + x][j * subSize + y];
                    }
                }
                subGrid[j*subSize+i] = new Grid.SubGrid( gridCells );
            }
        }
    }

    public int size() {
        return size;
    }

    public int subSize() {
        return subSize;
    }

    /**
     * Gets the cell at position (x,y)
     * @param x the x position (between 1 and size)
     * @param y the y position (between 1 and size)
     * @return the cell
     */
    public Cell getCell( int x, int y ) {
        return cells[x-1][y-1];
    }

    public Grid.Row getRow( int y ) {
        return rows[y-1];
    }

    public Grid.Column getColumn( int x ) {
        return cols[x-1];
    }

    public Grid.SubGrid getSubGrid( int n ) {
        if ( n <= 0 || n > size )
            throw new IndexOutOfBoundsException( "n cannot be <= 0 or > size. Passed " + n );
        return subGrid[n-1];
    }

    public Grid.SubGrid getSubGrid( int x, int y ) {
        if ( x <= 0 || x > subSize || y <= 0 || y > subSize )
            throw new IndexOutOfBoundsException( "x and y must be > 0 and <= subSize. Passed x=" + x +", y=" + y );
        int n = (y-1)*subSize + x;
        return getSubGrid( n );
    }

    public Grid.SubGrid getSubGridContaining( int x, int y ) {
        if ( x <= 0 || x > size || y <= 0 || y > size )
            throw new IndexOutOfBoundsException( "x and y must be > 0 and <= size. Passed x=" + x +", y=" + y );
        return getSubGrid( (x-1)/subSize+1, (y-1)/subSize+1 );
    }

    @Override
    public Iterator<Cell> iterator () {
        return new BoardIterator();
    }

    public void print() {
        String divide = "+";
        for ( int i = 0; i < subSize; i++ ) {
            for ( int j = 0; j < subSize; j++ )
                divide += "---";
            divide += '+';
        }
        for ( int j = 1; j <= size; j++ ) {
            if ( j % subSize == 1 )
                System.out.println( divide );
            for ( int i = 1; i <= size; i++ ) {
                if ( i % subSize == 1 )
                    System.out.print( '|' );
                Cell cell = getCell( i, j );
                System.out.print( cell.toString() );
            }
            System.out.println( '|' );
        }
        System.out.println( divide );
    }

}
