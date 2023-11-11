package org.gol;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    private static final long GAME_LOOP_DURATION = 500L;
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    private final Grid grid;

    public Controller(int height, int width) {
        grid = new Grid(height, width);
    }

    public void start() {
        Runnable gameLoop = () -> {
            System.out.println(grid);
            processNeighbors();
            processCellsState();
        };
        SCHEDULER.scheduleAtFixedRate(gameLoop, 0L, GAME_LOOP_DURATION, TimeUnit.MILLISECONDS);
    }

    private void processNeighbors() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                grid.getCell(i, j).calculateAliveNeighbors();
            }
        }
    }

    private void processCellsState() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                grid.getCell(i, j).updateStateBasedOnNeighbors();
            }
        }
    }
}
