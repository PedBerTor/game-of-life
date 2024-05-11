package com.github.pedbertor.gol;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Grid.
 *
 * <p>A {@code Grid} instance can be seen as the game board of this implementation of
 * Conways's Game of Life. It is, essentially, a two-dimensional array of {@link Cell}
 * instances.
 *
 * @author Pedro Bernaldez
 */
public class Grid {

    private final int height;
    private final int width;
    private final Cell[][] cells;

    /**
     * Instantiates a new Grid with the given size and filled with dead cells.
     *
     * @param height the height
     * @param width  the width
     */
    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        fillGrid();
        addNeighbors();
    }

    private void fillGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    private void addNeighbors() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j].setNeighbors(getNeighbors(i, j));
            }
        }
    }

    private Set<Cell> getNeighbors(int verticalReference, int horizontalReference) {
        Stream.Builder<Cell> builder = Stream.builder();

        /*
         * Positions of all possible neighbors for a cell at (i, j):
         *      (i-1, j-1)
         *      (i-1, j)
         *      (i, j-1)
         *      (i+1, j+1)
         *      (i+1, j)
         *      (i, j+1)
         *      (i-1, j+1)
         *      (i+1, j-1)
         */
        builder.add(getCell(verticalReference - 1, horizontalReference - 1));
        builder.add(getCell(verticalReference - 1, horizontalReference));
        builder.add(getCell(verticalReference, horizontalReference - 1));
        builder.add(getCell(verticalReference + 1, horizontalReference + 1));
        builder.add(getCell(verticalReference + 1, horizontalReference));
        builder.add(getCell(verticalReference, horizontalReference + 1));
        builder.add(getCell(verticalReference - 1, horizontalReference + 1));
        builder.add(getCell(verticalReference + 1, horizontalReference - 1));

        return builder.build()
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * Gets all cells.
     *
     * @return the cells
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * Gets the cell located in the given position, if any.
     *
     * @param verticalPosition   the vertical position
     * @param horizontalPosition the horizontal position
     * @return the cell or {@code null} if it does not exist
     */
    public Cell getCell(int verticalPosition, int horizontalPosition) {
        try {
            return cells[verticalPosition][horizontalPosition];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Grid grid) {
            if ((grid.height == this.height) && (grid.width == this.width)) {
                for (int i = 0; i < grid.height; i++) {
                    for (int j = 0; j < grid.width; j++) {
                        if (grid.cells[i][j].isAlive() != this.cells[i][j].isAlive()) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = height;
        result = 31 * result + width;
        result = 31 * result + Arrays.deepHashCode(cells);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Height: ").append(height).append("\n");
        builder.append("Width: ").append(width).append("\n");
        builder.append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isAlive()) {
                    builder.append("O").append(" ");
                } else {
                    builder.append("X").append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
