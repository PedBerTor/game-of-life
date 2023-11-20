package org.gol.controller;

import org.gol.processor.cellstate.CellStateProcessor;
import org.gol.processor.cellstate.FunctionalCellStateProcessor;
import org.gol.processor.neighbor.FunctionalNeighborProcessor;
import org.gol.processor.neighbor.NeighborProcessor;

public class ControllerBuilder {

    private int gridHeight = 20;
    private int gridWidth = 60;
    private NeighborProcessor neighborProcessor;
    private CellStateProcessor cellStateProcessor;
    private long gameLoopDuration = 500L;

    public ControllerBuilder setGridHeight(int gridHeight) {
        if (gridHeight < 3) {
            throw new RuntimeException("A grid must be at least three cells high");
        }
        this.gridHeight = gridHeight;
        return this;
    }

    public ControllerBuilder setGridWidth(int gridWidth) {
        if (gridWidth < 3) {
            throw new RuntimeException("A grid must be at least three cells wide");
        }
        this.gridWidth = gridWidth;
        return this;
    }

    public ControllerBuilder setNeighborProcessor(NeighborProcessor neighborProcessor) {
        this.neighborProcessor = neighborProcessor;
        return this;
    }

    public ControllerBuilder setCellStateProcessor(CellStateProcessor cellStateProcessor) {
        this.cellStateProcessor = cellStateProcessor;
        return this;
    }

    public ControllerBuilder setGameLoopDuration(long gameLoopDuration) {
        this.gameLoopDuration = gameLoopDuration;
        return this;
    }

    public Controller create() {
        if (neighborProcessor == null) {
            neighborProcessor = new FunctionalNeighborProcessor();
        }
        if (cellStateProcessor == null) {
            cellStateProcessor = new FunctionalCellStateProcessor();
        }
        return new Controller(gridHeight, gridWidth, neighborProcessor, cellStateProcessor, gameLoopDuration);
    }
}
