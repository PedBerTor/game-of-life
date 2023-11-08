package org.gol;

public class Cell {

    private final int verticalPosition;
    private final int horizontalPosition;

    private boolean isAlive = false;
    private int aliveNeighbors = 0;

    public Cell(int verticalPosition, int horizontalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void switchState() {
        isAlive = !isAlive;
    }

    public void addAliveNeighbor() {
        aliveNeighbors++;
    }

    public void updateStateBasedOnNeighbors() {
        if (isAliveAndDies()) {
            isAlive = false;
        } else if (isDeadAndRevives()) {
            isAlive = true;
        }
        aliveNeighbors = 0;
    }

    private boolean isAliveAndDies() {
        return isAlive && (aliveNeighbors < 2 || aliveNeighbors > 3);
    }

    private boolean isDeadAndRevives() {
        return !isAlive && aliveNeighbors == 3;
    }
}
