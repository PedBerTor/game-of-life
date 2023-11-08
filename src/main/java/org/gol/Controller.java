package org.gol;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Controller {

    private static final long GAME_LOOP_DURATION = 500L;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
        scheduler.scheduleAtFixedRate(gameLoop, 0L, GAME_LOOP_DURATION, TimeUnit.MILLISECONDS);
    }

    private void processNeighbors() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Cell cell = grid.getCell(i, j);
                getNeighbors(cell)
                        .filter(Objects::nonNull)
                        .filter(Cell::isAlive)
                        .forEach((aliveNeighbor) -> cell.addAliveNeighbor());
            }
        }
    }

    private Stream<Cell> getNeighbors(Cell cell) {
        int vPos = cell.getVerticalPosition();
        int hPos = cell.getHorizontalPosition();

        Cell firstNeighbor = grid.getCell(vPos - 1, hPos - 1);
        Cell secondNeighbor = grid.getCell(vPos - 1, hPos);
        Cell thirdNeighbor = grid.getCell(vPos, hPos - 1);
        Cell fourthNeighbor = grid.getCell(vPos + 1, hPos + 1);
        Cell fifthNeighbor = grid.getCell(vPos + 1, hPos);
        Cell sixthNeighbor = grid.getCell(vPos, hPos + 1);
        Cell seventhNeighbor = grid.getCell(vPos - 1, hPos + 1);
        Cell eightNeighbor = grid.getCell(vPos + 1, hPos - 1);

        return Stream.of(firstNeighbor, secondNeighbor, thirdNeighbor, fourthNeighbor,
                fifthNeighbor, sixthNeighbor, seventhNeighbor, eightNeighbor);
    }

    private void processCellsState() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                grid.getCell(i, j).updateStateBasedOnNeighbors();
            }
        }
    }
}
