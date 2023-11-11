package org.gol;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid {

    private final int height;
    private final int width;
    private final Cell[][] cells;

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
                cells[i][j] = new Cell();
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

    public Cell getCell(int verticalPosition, int horizontalPosition) {
        try {
            return cells[verticalPosition][horizontalPosition];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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
