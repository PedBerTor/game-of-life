package org.gol.controller;

import org.gol.Grid;
import org.gol.processor.cellstate.CellStateProcessor;
import org.gol.processor.neighbor.NeighborProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    private final Grid grid;
    private final NeighborProcessor neighborProcessor;
    private final CellStateProcessor cellStateProcessor;
    private final long gameLoopDuration;

    Controller(int gridHeight, int gridWidth, NeighborProcessor neighborProcessor,
               CellStateProcessor cellStateProcessor, long gameLoopDuration) {
        grid = new Grid(gridHeight, gridWidth);
        this.neighborProcessor = neighborProcessor;
        this.cellStateProcessor = cellStateProcessor;
        this.gameLoopDuration = gameLoopDuration;
    }

    public void start() {
        Runnable gameLoop = () -> {
            System.out.println(grid);
            neighborProcessor.process(grid);
            cellStateProcessor.process(grid);
        };
        SCHEDULER.scheduleAtFixedRate(gameLoop, 0L, gameLoopDuration, TimeUnit.MILLISECONDS);
    }
}
