package org.gol.processor.cellstate;

import org.gol.Cell;
import org.gol.Grid;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Functional implementation of {@link CellStateProcessor}.
 *
 * @author Pedro Bernaldez
 */
public class FunctionalCellStateProcessor implements CellStateProcessor {

    @Override
    public void process(Grid grid) {
        Arrays.stream(grid.getCells())
                .flatMap(Stream::of)
                .forEach(Cell::updateStateBasedOnNeighbors);
    }
}
