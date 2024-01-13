package com.github.pedbertor.gol.processor.cellstate;

import com.github.pedbertor.gol.Grid;

/**
 * Iterative implementation of {@link CellStateProcessor}.
 *
 * @author Pedro Bernaldez
 */
public class IterativeCellStateProcessor implements CellStateProcessor {

    @Override
    public void process(Grid grid) {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                grid.getCell(i, j).updateStateBasedOnNeighbors();
            }
        }
    }
}
