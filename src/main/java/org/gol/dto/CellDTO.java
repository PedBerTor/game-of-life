package org.gol.dto;

import org.gol.Grid;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Cell DTO for {@link Grid} serialization/deserialization.
 *
 * <p>A {@code CellDTO} instance represents the position on both axes of an alive cell
 * in a {@link Grid}. This is essentially all cell information one may need to serialize
 * and deserialize {@link Grid} instances, because cells can be either dead or alive, and
 * the remaining cells will be dead.
 *
 * <p>Use the {@link #fromGrid(Grid) fromGrid} method to extract all alive cells from a
 * {@link Grid} in the form of a {@code Set} of {@code CellDTO} instances:
 *
 * <pre>
 * Set<CellDTO> aliveCells = CellDTO.fromGrid(grid);
 * </pre>
 *
 * @author Jo Chong Min
 */
public class CellDTO {

    private int verticalPosition;
    private int horizontalPosition;

    /**
     * Instantiates a new Cell DTO.
     */
    public CellDTO() {
        super();
    }

    /**
     * Gets vertical position.
     *
     * @return the vertical position
     */
    public int getVerticalPosition() {
        return verticalPosition;
    }

    /**
     * Sets vertical position.
     *
     * @param verticalPosition the vertical position
     */
    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    /**
     * Gets horizontal position.
     *
     * @return the horizontal position
     */
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    /**
     * Sets horizontal position.
     *
     * @param horizontalPosition the horizontal position
     */
    public void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    /**
     * Extract all alive cells from the given {@link Grid}.
     *
     * <p>For each cell in the grid, if alive, an instance of {@code CellDTO} is created based
     * on its vertical and horizontal position. The {@code CellDTO} instances are collected
     * into a {@code Set}.
     *
     * @param grid the grid
     * @return the alive cells
     */
    public static Set<CellDTO> fromGrid(Grid grid) {
        Set<CellDTO> res = new LinkedHashSet<>();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.getCell(i, j).isAlive()) {
                    CellDTO cellDTO = new CellDTO();
                    cellDTO.setVerticalPosition(i);
                    cellDTO.setHorizontalPosition(j);
                    res.add(cellDTO);
                }
            }
        }
        return res;
    }
}
