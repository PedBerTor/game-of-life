package org.gol.processor.cellstate;

import org.gol.Grid;

/**
 * Interface representing a cell state processor.
 *
 * @author Pedro Bernaldez
 */
public interface CellStateProcessor {

    /**
     * This method is invoked in the game loop to process the cells state
     * in the given {@link Grid}.
     *
     * @param grid the grid
     */
    void process(Grid grid);
}
