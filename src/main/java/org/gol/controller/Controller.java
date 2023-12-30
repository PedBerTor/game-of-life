package org.gol.controller;

import org.gol.Grid;
import org.gol.processor.cellstate.CellStateProcessor;
import org.gol.processor.neighbor.NeighborProcessor;

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

    /**
     * Instantiates a new Controller.
     *
     * @param gridHeight         the grid height
     * @param gridWidth          the grid width
     * @param gameLoopDuration   the game loop duration
     * @param neighborProcessor  the neighbor processor
     * @param cellStateProcessor the cell state processor
     */
    Controller(int gridHeight, int gridWidth, long gameLoopDuration,
               NeighborProcessor neighborProcessor, CellStateProcessor cellStateProcessor) {
        grid = new Grid(gridHeight, gridWidth);
        this.gameLoopDuration = gameLoopDuration;
        this.neighborProcessor = neighborProcessor;
        this.cellStateProcessor = cellStateProcessor;
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
            System.out.println(grid);
            neighborProcessor.process(grid);
            cellStateProcessor.process(grid);
        };
        SCHEDULER.scheduleAtFixedRate(gameLoop, 0L, gameLoopDuration, TimeUnit.MILLISECONDS);
    }
}
