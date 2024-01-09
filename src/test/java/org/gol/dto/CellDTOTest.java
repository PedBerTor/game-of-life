package org.gol.dto;

import org.gol.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link CellDTO}.
 *
 * @author Pedro Bernaldez
 */
public class CellDTOTest {

    private static final int GRID_HEIGHT = 5;
    private static final int GRID_WIDTH = 5;
    private static final int[][] ALIVE_CELLS = new int[][]{
            new int[]{2, 1},
            new int[]{2, 2},
            new int[]{2, 3}
    };

    private final Grid grid = createTestGrid();

    @Test
    public void checkFromGrid() {
        List<CellDTO> aliveCells = CellDTO.fromGrid(grid).stream().toList();
        assertEquals(ALIVE_CELLS.length, aliveCells.size());
        for (int i = 0; i < ALIVE_CELLS.length; i++) {
            assertEquals(ALIVE_CELLS[i][0], aliveCells.get(i).getVerticalPosition());
            assertEquals(ALIVE_CELLS[i][1], aliveCells.get(i).getHorizontalPosition());
        }
    }

    private Grid createTestGrid() {
        Grid grid = new Grid(GRID_HEIGHT, GRID_WIDTH);
        for (int[] aliveCell : ALIVE_CELLS) {
            grid.getCell(aliveCell[0], aliveCell[1]).switchState();
        }
        return grid;
    }
}
