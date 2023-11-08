package org.gol;

public class Grid {

    private final int height;
    private final int width;
    private final Cell[][] cells;

    public Grid(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        fillGrid();
    }

    private void fillGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(int verticalPosition, int horizontalPosition) {
        try {
            return cells[verticalPosition][horizontalPosition];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
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
