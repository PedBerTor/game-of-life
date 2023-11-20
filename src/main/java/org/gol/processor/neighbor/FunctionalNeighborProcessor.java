package org.gol.processor.neighbor;

import org.gol.Cell;
import org.gol.Grid;

import java.util.Arrays;
import java.util.stream.Stream;

public class FunctionalNeighborProcessor implements NeighborProcessor {

    @Override
    public void process(Grid grid) {
        Arrays.stream(grid.getCells())
                .flatMap(Stream::of)
                .forEach(Cell::calculateAliveNeighbors);
    }
}
