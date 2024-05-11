package com.github.pedbertor.gol;

import com.github.pedbertor.gol.gui.GUI;

import java.util.Collections;
import java.util.Set;

/**
 * Cell.
 *
 * <p>A {@code Cell} instance represents the fundamental unit of Conways's Game of
 * Life. According to the rules of Conway's Game of Life, a {@code Cell} can be dead
 * or alive depending on its neighbors, which are the cells that are horizontally,
 * vertically or diagonally adjacent.
 *
 * @author Pedro Bernaldez
 */
public class Cell {

    private final int verticalPosition;
    private final int horizontalPosition;

    private boolean isAlive = false;
    private Set<Cell> neighbors = Collections.emptySet();
    private int aliveNeighbors = 0;

    /**
     * Instantiates a new Cell.
     *
     * @param verticalPosition   the vertical position
     * @param horizontalPosition the horizontal position
     */
    public Cell(int verticalPosition, int horizontalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }

    /**
     * Checks whether the cell is alive.
     *
     * @return true if the cell is alive; false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Switches the state of the cell.
     */
    public void switchState() {
        isAlive = !isAlive;
    }

    /**
     * Kills the cell.
     */
    public void kill() {
        isAlive = false;
    }

    /**
     * Sets neighbors.
     *
     * @param neighbors the neighbors
     * @throws IllegalArgumentException if the number of neighbors is not valid
     */
    public void setNeighbors(Set<Cell> neighbors) {
        if (neighbors.size() < 3 || neighbors.size() > 8) {
            throw new IllegalArgumentException("A cell must have between one and eight neighbors");
        }
        this.neighbors = neighbors;
    }

    /**
     * Calculate alive neighbors.
     */
    public void calculateAliveNeighbors() {
        aliveNeighbors = (int) neighbors.stream()
                .filter(Cell::isAlive)
                .count();
    }

    /**
     * Update cell state based on its neighbors, according to the rules of Conway's Game of Life.
     */
    public void updateStateBasedOnNeighbors() {
        if (isAliveAndDies() || isDeadAndRevives()) {
            switchState();
            GUI.switchCellButtonState(verticalPosition, horizontalPosition);
        }
        aliveNeighbors = 0;
    }

    private boolean isAliveAndDies() {
        return isAlive && (aliveNeighbors < 2 || aliveNeighbors > 3);
    }

    private boolean isDeadAndRevives() {
        return !isAlive && aliveNeighbors == 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (verticalPosition != cell.verticalPosition) return false;
        return horizontalPosition == cell.horizontalPosition;
    }

    @Override
    public int hashCode() {
        int result = verticalPosition;
        result = 31 * result + horizontalPosition;
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "verticalPosition=" + verticalPosition +
                ", horizontalPosition=" + horizontalPosition +
                ", isAlive=" + isAlive +
                '}';
    }
}
