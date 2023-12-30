package org.gol.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.gol.Grid;
import org.gol.dto.GridDTO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * Set of utilities for {@link Grid}.
 *
 * @author Jo Chong Min
 */
public class GridUtils {

    private static final String BASE_PATH = "grids/";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private GridUtils() {
        super();
    }

    /**
     * Load a {@link Grid} instance from a JSON file.
     *
     * @param fileName the file name
     * @return the grid
     * @throws IOException if an error occurred
     */
    public static Grid loadFromFile(String fileName) throws IOException {
        String filePath = BASE_PATH + fileName + ".json";
        try (InputStream inputStream = GridUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            GridDTO gridDTO = GSON.fromJson(reader, GridDTO.class);
            return GridDTO.toGrid(gridDTO);
        }
    }

    /**
     * Save the given {@link Grid} to a JSON file with the given name.
     *
     * <p>Files are saved in the /resources/grids directory.
     *
     * <p>The following example shows how a {@link Grid} instance is serialized
     * as a JSON file:
     *
     * <pre>
     * Grid grid = new Grid(3, 3);
     * grid.getCell(1, 1).switchState();
     *
     * [0] [0] [0]      [0] = Dead cell
     * [0] [1] [0]      [1] = Alive cell
     * [0] [0] [0]
     *
     * GridUtils.saveToFile(grid, "grid");
     *
     * {
     *   "height": 3,
     *   "width": 3,
     *   "aliveCells": [
     *     {
     *       "verticalPosition": 1,
     *       "horizontalPosition": 1
     *     }
     *   ]
     * }
     * </pre>
     *
     * @param grid     the grid
     * @param fileName the file name
     */
    public static void saveToFile(Grid grid, String fileName) {
        String path = "src/main/resources/" + BASE_PATH + fileName + ".json";
        try {
            GridDTO gridDTO = GridDTO.fromGrid(grid);
            String json = GSON.toJson(gridDTO);
            Path filePath = Paths.get(path);
            if (Files.exists(filePath)) {
                // TODO: Overwrite file
            } else {
                Files.createFile(filePath);
                Files.writeString(filePath, json);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
