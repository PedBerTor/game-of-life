package com.github.pedbertor.gol.util;

import com.github.pedbertor.gol.dto.CellDTO;
import com.github.pedbertor.gol.gui.GUI;
import com.github.pedbertor.gol.gui.component.panel.GridPanel;
import com.github.pedbertor.gol.dto.GridDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Set of utilities for {@link GridPanel}.
 *
 * @author Jo Chong Min
 */
public class GridPanelUtils {

    private static final String BASE_PATH = "src/main/resources/grids/";
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private GridPanelUtils() {
        super();
    }

    /**
     * Load the state of the given {@link GridPanel} instance from a JSON file.
     *
     * @param gridPanel the grid panel
     * @param fileName  the file name
     * @throws IOException                   if an error occurred
     * @throws UnsupportedOperationException if the file is not GUI compatible
     */
    public static void loadStateFromFile(GridPanel gridPanel, String fileName) throws IOException {
        String filePath = BASE_PATH + fileName;
        try (FileReader fileReader = new FileReader(filePath)) {
            GridDTO gridDTO = GSON.fromJson(fileReader, GridDTO.class);
            if (gridDTO.getHeight() != GUI.GRID_HEIGHT || gridDTO.getWidth() != GUI.GRID_WIDTH) {
                throw new UnsupportedOperationException("This file is not GUI compatible");
            }
            gridPanel.reset();
            for (CellDTO aliveCell : gridDTO.getAliveCells()) {
                gridPanel.getCellButton(
                        aliveCell.getVerticalPosition(),
                        aliveCell.getHorizontalPosition()
                ).switchStateOnClickOrLoad();
            }
        }
    }

    /**
     * Save the state of the given {@link GridPanel} to a JSON file with the given name.
     *
     * <p>Files are saved in the src/main/resources/grids directory. If a file with the
     * given name already exists, its content is overwritten. Otherwise, the file is
     * created and filled with the JSON resulted from the {@link GridPanel} serialization.
     *
     * <p>The following example shows how a {@link GridPanel} instance is serialized
     * as a JSON file:
     *
     * <pre>
     * GridPanel gridPanel = new GridPanel();
     * gridPanel.getCellButton(1, 1).switchState();
     *
     * [0] [0] [0]  .  .  .    [0] = Dead cell
     * [0] [1] [0]  .  .  .    [1] = Alive cell
     * [0] [0] [0]  .  .  .
     *  .   .   .
     *  .   .   .
     *  .   .   .
     *
     * GridUtils.saveToFile(gridPanel, "grid");
     *
     * {
     *   "height": 30,
     *   "width": 48,
     *   "aliveCells": [
     *     {
     *       "verticalPosition": 1,
     *       "horizontalPosition": 1
     *     }
     *   ]
     * }
     * </pre>
     *
     * @param gridPanel the grid panel
     * @param fileName  the file name
     * @throws IOException if an error occurred
     */
    public static void saveStateToFile(GridPanel gridPanel, String fileName) throws IOException {
        String resourcePath = BASE_PATH + fileName;
        try (FileWriter fileWriter = new FileWriter(resourcePath)) {
            fileWriter.write(GSON.toJson(GridDTO.fromGridPanel(gridPanel)));
        }
    }
}
