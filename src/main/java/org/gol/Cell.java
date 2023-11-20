package org.gol;

import java.util.Collections;
import java.util.Set;

public class Cell {

    private boolean isAlive = false;
    private Set<Cell> neighbors = Collections.emptySet();
    private int aliveNeighbors = 0;

    public Cell() {
        super();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void switchState() {
        isAlive = !isAlive;
    }

    public void setNeighbors(Set<Cell> neighbors) {
        if (neighbors.size() < 3 || neighbors.size() > 8) {
            throw new RuntimeException("A cell must have between one and eight neighbors");
        }
        this.neighbors = neighbors;
    }

    public void calculateAliveNeighbors() {
        aliveNeighbors = (int) neighbors.stream()
                .filter(Cell::isAlive)
                .count();
    }

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
