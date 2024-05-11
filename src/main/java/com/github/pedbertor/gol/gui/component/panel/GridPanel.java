package com.github.pedbertor.gol.gui.component.panel;

import com.github.pedbertor.gol.gui.GUI;
import com.github.pedbertor.gol.gui.component.button.CellButton;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Panel component that represents a grid of cells.
 *
 * @author Pedro Bernaldez
 */
public class GridPanel extends JPanel {

    private static final int GAP = 2;

    private final CellButton[][] cells = new CellButton[GUI.GRID_HEIGHT][GUI.GRID_WIDTH];

    /**
     * Instantiates a new Grid Panel.
     */
    public GridPanel() {
        super();

        setLayout(new GridLayout(GUI.GRID_HEIGHT, GUI.GRID_WIDTH, GAP, GAP));
        fillGridPanel();
    }

    private void fillGridPanel() {
        for (int i = 0; i < GUI.GRID_HEIGHT; i++) {
            for (int j = 0; j < GUI.GRID_WIDTH; j++) {
                CellButton cellButton = new CellButton(i, j);
                cells[i][j] = cellButton;
                add(cellButton);
            }
        }
    }

    /**
     * Gets the cell button located in the given position, if any.
     *
     * @param verticalPosition   the vertical position
     * @param horizontalPosition the horizontal position
     * @return the cell button
     * @throws NoSuchElementException if the cell button is not found
     */
    public CellButton getCellButton(int verticalPosition, int horizontalPosition) {
        return Arrays.stream(cells)
                .flatMap(Stream::of)
                .filter((cellButton) -> cellButton.getVerticalPosition() == verticalPosition
                        && cellButton.getHorizontalPosition() == horizontalPosition)
                .findFirst()
                .orElseThrow();
    }

    /**
     * Gets all alive cell buttons.
     *
     * @return the alive cell buttons
     */
    public Set<CellButton> getAliveCellButtons() {
        return Arrays.stream(cells)
                .flatMap(Stream::of)
                .filter(CellButton::isAlive)
                .collect(Collectors.toSet());
    }

    /**
     * Resets the state of all cells, both internally and externally.
     */
    public void reset() {
        Arrays.stream(cells)
                .flatMap(Stream::of)
                .forEach(CellButton::kill);
        GUI.CONTROLLER.reset();
    }
}
