package org.gol.processor.neighbor;

import org.gol.Grid;

/**
 * Iterative implementation of {@link NeighborProcessor}.
 *
 * @author Pedro Bernaldez
 */
public class IterativeNeighborProcessor implements NeighborProcessor {

    @Override
    public void process(Grid grid) {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                grid.getCell(i, j).calculateAliveNeighbors();
            }
        }
    }
}
