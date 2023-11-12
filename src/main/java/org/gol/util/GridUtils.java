package org.gol.util;

import org.gol.Grid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class GridUtils {

    private static final String BASE_PATH = "grids/";
    // TODO: Instantiate Gson object as constant using GsonBuilder (don't forget option to pretty print)

    private GridUtils() {
        super();
    }

    public static Grid loadFromFile(String fileName) {
        Grid res = null;
        String filePath = BASE_PATH + fileName + ".json";
        try (InputStream inputStream = GridUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            res = null; // TODO: Deserialize Reader (JSON) into Grid
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static void saveToFile(Grid grid, String fileName) {
        String filePath = "src/main/resources/" + BASE_PATH + fileName + ".json";
        try {
            String jsonGrid = ""; // TODO: Serialize Grid into String (JSON)
            Path createdFile = Files.createFile(Paths.get(filePath));
            Files.writeString(createdFile, jsonGrid);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
