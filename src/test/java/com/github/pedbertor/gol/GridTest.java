package com.github.pedbertor.gol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Grid}.
 *
 * @author Pedro Bernaldez
 */
public class GridTest {

    private static final int GRID_HEIGHT = 4;
    private static final int GRID_WIDTH = 20;
    private static final String TO_STRING_EXPECTED_OUTPUT = """
            Height: 4
            Width: 20
                        
            X X X X X X X X X X X X X X X X X X X X\s
            X X X X X X X X X X X X X X X X X X X X\s
            X X X X X X X X X X X X X X X X X X X X\s
            X X X X X X X X X X X X X X X X X X X X\s
            """;

    private final Grid grid = new Grid(GRID_HEIGHT, GRID_WIDTH);

    @Test
    public void checkGetCell() {
        grid.getCell(0, 0).switchState();
        assertTrue(grid.getCell(0, 0).isAlive());
        grid.getCell(0, 0).switchState();
        assertFalse(grid.getCell(0, 0).isAlive());
    }

    @Test
    public void checkEqualsWithEqualGrid() {
        Grid equalGrid = new Grid(GRID_HEIGHT, GRID_WIDTH);
        assertEquals(grid, equalGrid);
    }

    @Test
    public void checkEqualsWithDifferentHeightGrid() {
        Grid differentHeightGrid = new Grid(GRID_HEIGHT - 1, GRID_WIDTH);
        assertNotEquals(grid, differentHeightGrid);
    }

    @Test
    public void checkEqualsWithDifferentWidthGrid() {
        Grid differentWidthGrid = new Grid(GRID_HEIGHT, GRID_WIDTH - 1);
        assertNotEquals(grid, differentWidthGrid);
    }

    @Test
    public void checkEqualsWithDifferentCellsStateGrid() {
        Grid differentCellsStateGrid = new Grid(GRID_HEIGHT, GRID_WIDTH);
        differentCellsStateGrid.getCell(0, 0).switchState();
        assertNotEquals(grid, differentCellsStateGrid);
    }

    @Test
    public void checkToString() {
        assertEquals(TO_STRING_EXPECTED_OUTPUT, grid.toString());
    }
}
