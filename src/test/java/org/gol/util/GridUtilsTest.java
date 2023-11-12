package org.gol.util;

import org.gol.Grid;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridUtilsTest {

    private static final int GRID_HEIGHT = 5;
    private static final int GRID_WIDTH = 5;
    private static final int[][] ALIVE_CELLS = new int[][]{
            new int[]{2, 1},
            new int[]{2, 2},
            new int[]{2, 3}
    };
    private static final String INPUT_TEST_FILE_NAME = "sample";
    private static final String OUTPUT_TEMP_FILE_NAME = "temp-file";
    private static final Path OUTPUT_TEMP_FILE_PATH = Paths.get("src/main/resources/grids/temp-file.json");
    private static final String OUTPUT_TEMP_FILE_CONTENT = """
            {
                "height": 5,
                "width": 5,
                "aliveCells": [
                    {
                        "verticalPosition": 2,
                        "horizontalPosition": 1
                    },
                    {
                        "verticalPosition": 2,
                        "horizontalPosition": 2
                    },
                    {
                        "verticalPosition": 2,
                        "horizontalPosition": 3
                    }
                ]
            }
            """;

    private final Grid grid = createTestGrid();

    @AfterAll
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(OUTPUT_TEMP_FILE_PATH);
    }

    @Test
    public void loadFromFile() {
        Grid loadedGrid = GridUtils.loadFromFile(INPUT_TEST_FILE_NAME);
        assertEquals(grid, loadedGrid);
    }

    @Test
    public void saveToFile() throws IOException {
        GridUtils.saveToFile(grid, OUTPUT_TEMP_FILE_NAME);
        String serializedSavedGrid = getFileContent();
        assertEquals(OUTPUT_TEMP_FILE_CONTENT.trim(), serializedSavedGrid.trim());
    }

    private Grid createTestGrid() {
        Grid grid = new Grid(GRID_HEIGHT, GRID_WIDTH);
        for (int[] aliveCell : ALIVE_CELLS) {
            grid.getCell(aliveCell[0], aliveCell[1]).switchState();
        }
        return grid;
    }

    private String getFileContent() throws IOException {
        return Files.readString(OUTPUT_TEMP_FILE_PATH).replace("\r\n", "\n");
    }
}
