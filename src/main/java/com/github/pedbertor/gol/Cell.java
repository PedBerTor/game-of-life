package com.github.pedbertor.gol;

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

    private boolean isAlive = false;
    private Set<Cell> neighbors = Collections.emptySet();
    private int aliveNeighbors = 0;

    /**
     * Instantiates a new Cell.
     */
    public Cell() {
        super();
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Switch state.
     */
    public void switchState() {
        isAlive = !isAlive;
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
