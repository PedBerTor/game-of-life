package org.gol.dto;

import org.gol.Grid;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link GridDTO}.
 *
 * @author Pedro Bernaldez
 */
public class GridDTOTest {

    private static final int GRID_HEIGHT = 5;
    private static final int GRID_WIDTH = 5;
    private static final int[][] ALIVE_CELLS = new int[][]{
            new int[]{2, 1},
            new int[]{2, 2},
            new int[]{2, 3}
    };

    private final Grid grid = createTestGrid();
    private final GridDTO gridDTO = createTestGridDTO();

    @Test
    public void checkToGrid() {
        Grid gridFromGridDTO = GridDTO.toGrid(gridDTO);
        assertEquals(GRID_HEIGHT, gridFromGridDTO.getHeight());
        assertEquals(GRID_WIDTH, gridFromGridDTO.getWidth());
        for (int[] aliveCell : ALIVE_CELLS) {
            assertTrue(gridFromGridDTO.getCell(aliveCell[0], aliveCell[1]).isAlive());
        }
    }

    @Test
    public void checkFromGrid() {
        GridDTO gridDTOFromGrid = GridDTO.fromGrid(grid);
        assertEquals(GRID_HEIGHT, gridDTOFromGrid.getHeight());
        assertEquals(GRID_WIDTH, gridDTOFromGrid.getWidth());
        int i = 0;
        for (CellDTO aliveCell : gridDTOFromGrid.getAliveCells()) {
            assertEquals(ALIVE_CELLS[i][0], aliveCell.getVerticalPosition());
            assertEquals(ALIVE_CELLS[i][1], aliveCell.getHorizontalPosition());
            i++;
        }
    }

    private Grid createTestGrid() {
        Grid grid = new Grid(GRID_HEIGHT, GRID_WIDTH);
        for (int[] aliveCell : ALIVE_CELLS) {
            grid.getCell(aliveCell[0], aliveCell[1]).switchState();
        }
        return grid;
    }

    private GridDTO createTestGridDTO() {
        GridDTO gridDTO = new GridDTO();
        gridDTO.setHeight(GRID_HEIGHT);
        gridDTO.setWidth(GRID_WIDTH);
        Set<CellDTO> aliveCells = new LinkedHashSet<>();
        for (int[] aliveCell : ALIVE_CELLS) {
            CellDTO cellDTO = new CellDTO();
            cellDTO.setVerticalPosition(aliveCell[0]);
            cellDTO.setHorizontalPosition(aliveCell[1]);
            aliveCells.add(cellDTO);
        }
        gridDTO.setAliveCells(aliveCells);
        return gridDTO;
    }
}
