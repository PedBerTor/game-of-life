package org.gol.controller;

import org.gol.processor.cellstate.CellStateProcessor;
import org.gol.processor.cellstate.FunctionalCellStateProcessor;
import org.gol.processor.neighbor.FunctionalNeighborProcessor;
import org.gol.processor.neighbor.NeighborProcessor;

/**
 * Builder for {@link Controller}.
 *
 * <p>The following example shows how to use {@code ControllerBuilder} to build
 * a {@code Controller} instance:
 *
 * <pre>
 * Controller controller = new ControllerBuilder()
 *     .setGridHeight(8)
 *     .setGridWidth(8)
 *     .setGameLoopDuration(1000L)
 *     .create();
 * </pre>
 *
 * @author Pedro Bernaldez
 */
public class ControllerBuilder {

    /**
     * Default values if setters are not called
     */
    private int gridHeight = 20;
    private int gridWidth = 60;
    private long gameLoopDuration = 500L;

    /**
     * Processors are initialized by default in {@link #create() create}, if necessary
     */
    private NeighborProcessor neighborProcessor;
    private CellStateProcessor cellStateProcessor;

    /**
     * Instantiates a new Controller Builder.
     */
    public ControllerBuilder() {
        super();
    }

    /**
     * Sets grid height.
     *
     * @param gridHeight the grid height
     * @return a reference to this {@code ControllerBuilder} object
     * @throws IllegalArgumentException if the grid height is less than three
     */
    public ControllerBuilder setGridHeight(int gridHeight) {
        if (gridHeight < 3) {
            throw new IllegalArgumentException("A grid must be at least three cells high");
        }
        this.gridHeight = gridHeight;
        return this;
    }

    /**
     * Sets grid width.
     *
     * @param gridWidth the grid width
     * @return a reference to this {@code ControllerBuilder} object
     * @throws IllegalArgumentException if the grid width is less than three
     */
    public ControllerBuilder setGridWidth(int gridWidth) {
        if (gridWidth < 3) {
            throw new IllegalArgumentException("A grid must be at least three cells wide");
        }
        this.gridWidth = gridWidth;
        return this;
    }

    /**
     * Sets game loop duration.
     *
     * @param gameLoopDuration the game loop duration
     * @return a reference to this {@code ControllerBuilder} object
     */
    public ControllerBuilder setGameLoopDuration(long gameLoopDuration) {
        this.gameLoopDuration = gameLoopDuration;
        return this;
    }

    /**
     * Sets neighbor processor.
     *
     * @param neighborProcessor the neighbor processor
     * @return a reference to this {@code ControllerBuilder} object
     */
    public ControllerBuilder setNeighborProcessor(NeighborProcessor neighborProcessor) {
        this.neighborProcessor = neighborProcessor;
        return this;
    }

    /**
     * Sets cell state processor.
     *
     * @param cellStateProcessor the cell state processor
     * @return a reference to this {@code ControllerBuilder} object
     */
    public ControllerBuilder setCellStateProcessor(CellStateProcessor cellStateProcessor) {
        this.cellStateProcessor = cellStateProcessor;
        return this;
    }

    /**
     * Creates a {@link Controller} instance based on the parameters passed to
     * this {@code ControllerBuilder} object.
     *
     * @return the controller
     */
    public Controller create() {
        if (neighborProcessor == null) {
            neighborProcessor = new FunctionalNeighborProcessor();
        }
        if (cellStateProcessor == null) {
            cellStateProcessor = new FunctionalCellStateProcessor();
        }
        return new Controller(gridHeight, gridWidth, gameLoopDuration,
                neighborProcessor, cellStateProcessor);
    }
}
