package org.gol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    private static final int GRID_HEIGHT = 4;
    private static final int GRID_WIDTH = 20;
    private static final String TO_STRING_EXPECTED_OUTPUT = """
            Width: 20
            Height: 4
                        
            XXXXXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXXXXX
            XXXXXXXXXXXXXXXXXXXX
            """;

    private final Grid grid;

    public GridTest() {
        grid = new Grid(GRID_HEIGHT, GRID_WIDTH);
    }

    @Test
    public void checkToString() {
        assertEquals(TO_STRING_EXPECTED_OUTPUT, grid.toString());
    }
}
