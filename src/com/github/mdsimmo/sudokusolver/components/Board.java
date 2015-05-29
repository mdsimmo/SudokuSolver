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
    private final Grid[] rows, cols, subGrid;

    /**
     * Constructs a sudoku grid of the default 9x9 grid
     */
    public Board() {
        this( 9 );
    }

    /**
     * Constructs a sudoku grid. The board will be sizexsize square, thus, each cell will have size options. The normal
     * sudoku board has a size of 9;
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
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                cells[i][j] = new Cell(size);
            }
        }

        rows = new Grid[size];
        cols = new Grid[size];
        for ( int i = 0; i < size; i++ ) {
            Cell[] rowArray = new Cell[size];
            Cell[] colArray = new Cell[size];
            for ( int j = 0; j < size; j++ ) {
                rowArray[j] = cells[j][i];
                colArray[j] = cells[i][j];
            }
            rows[i] = new Grid( rowArray );
            cols[i] = new Grid( colArray );
        }

        subGrid = new Grid[size];
        for ( int i = 0; i < subSize; i++ ) {
            for ( int j = 0; j < subSize; j++ ) {
                Cell[] gridCells = new Cell[size];
                for ( int x = 0; x < subSize; x++ ) {
                    for ( int y = 0; y < subSize; y++ ) {
                        gridCells[y * subSize + x] = cells[i * subSize + x][j * subSize + y];
                    }
                }
                subGrid[j*subSize+i] = new Grid( gridCells );
            }
        }

    }

    public int size() {
        return size;
    }

    public int getSubSize() {
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

    public Grid getRow( int y ) {
        return rows[y-1];
    }

    public Grid getColumn( int x ) {
        return cols[x-1];
    }

    public Grid getSubGrid( int n ) {
        return subGrid[n-1];
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
                if ( cell.isSolved() )
                    System.out.print( " " + cell.getSolution() + " " );
                else
                    System.out.print( " - " );
            }
            System.out.println( '|' );
        }
        System.out.println( divide );
    }

}
