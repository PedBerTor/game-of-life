package com.github.pedbertor.gol.controller;

import com.github.pedbertor.gol.Grid;
import com.github.pedbertor.gol.processor.cellstate.CellStateProcessor;
import com.github.pedbertor.gol.processor.neighbor.NeighborProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Game controller.
 *
 * <p>Use {@link ControllerBuilder} to build a {@code Controller} instance with various
 * configuration options, such as grid size, game loop duration, {@link NeighborProcessor}
 * and {@link CellStateProcessor}.
 *
 * <p>The following example shows how {@code Controller} is used:
 *
 * <pre>
 * Controller controller = new ControllerBuilder().create();
 * controller.start();
 * </pre>
 *
 * @author Pedro Bernaldez
 */
public class Controller {

    /**
     * Instance of {@link ScheduledExecutorService} to run the game loop periodically.
     */
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    private final Grid grid;
    private final long gameLoopDuration;
    private final NeighborProcessor neighborProcessor;
    private final CellStateProcessor cellStateProcessor;
    private final boolean isConsoleOutputEnabled;

    /**
     * Instantiates a new Controller.
     *
     * @param gridHeight             the grid height
     * @param gridWidth              the grid width
     * @param gameLoopDuration       the game loop duration
     * @param neighborProcessor      the neighbor processor
     * @param cellStateProcessor     the cell state processor
     * @param isConsoleOutputEnabled the console output state
     */
    Controller(int gridHeight, int gridWidth, long gameLoopDuration,
               NeighborProcessor neighborProcessor, CellStateProcessor cellStateProcessor,
               boolean isConsoleOutputEnabled) {
        grid = new Grid(gridHeight, gridWidth);
        this.gameLoopDuration = gameLoopDuration;
        this.neighborProcessor = neighborProcessor;
        this.cellStateProcessor = cellStateProcessor;
        this.isConsoleOutputEnabled = isConsoleOutputEnabled;
    }

    /**
     * Defines the game loop as a sequence of three steps: (1) print the grid,
     * (2) process neighbors and (3) process cells state.
     *
     * <p>Once defined, a {@link ScheduledExecutorService} instance is used to
     * run the game loop periodically.
     */
    public void start() {
        Runnable gameLoop = () -> {
            if (isConsoleOutputEnabled) {
                System.out.println(grid);
            }
            neighborProcessor.process(grid);
            cellStateProcessor.process(grid);
        };
        SCHEDULER.scheduleAtFixedRate(gameLoop, 0L, gameLoopDuration, TimeUnit.MILLISECONDS);
    }
}
