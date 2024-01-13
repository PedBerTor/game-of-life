package com.github.pedbertor.gol.processor.cellstate;

import com.github.pedbertor.gol.Cell;
import com.github.pedbertor.gol.Grid;

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
