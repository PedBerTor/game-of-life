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
import java.util.Objects;

public class GridUtils {

    private static final String BASE_PATH = "grids/";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private GridUtils() {
        super();
    }

    public static Grid loadFromFile(String fileName) {
        Grid res = null;
        String filePath = BASE_PATH + fileName + ".json";
        try (InputStream inputStream = GridUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream));
            GridDTO gridDTO = GSON.fromJson(reader, GridDTO.class);
            res = GridDTO.toGrid(gridDTO);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

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
