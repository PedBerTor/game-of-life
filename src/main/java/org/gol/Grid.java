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
                cells[i][j] = new Cell();
            }
        }
    }

    StringBuilder builder = new StringBuilder();

    @Override
    public String toString() {
        builder.append("Widht = ").append(width).append("\n");
        builder.append("Height = ").append(height).append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (new Cell().isAlive()){
                    builder.append("O");
                } else {
                    builder.append("X");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
