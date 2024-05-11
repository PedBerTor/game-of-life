package com.github.pedbertor.gol.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import com.github.pedbertor.gol.gui.component.button.CellButton;
import com.github.pedbertor.gol.gui.component.panel.GridPanel;

/**
 * Cell DTO for {@link GridPanel} serialization/deserialization.
 *
 * <p>A {@code CellDTO} instance represents the position on both axes of an alive cell
 * in a {@link GridPanel} instance. This is essentially all cell information one may need
 * to serialize and deserialize {@link GridPanel} instances, because cells can be either
 * dead or alive, and the remaining cells will be dead.
 *
 * <p>Use the {@link #fromGridPanel(GridPanel) fromGridPanel} method to extract all alive
 * cells from a {@link GridPanel} instance in the form of a {@code Set} of {@code CellDTO}
 * instances:
 *
 * <pre>
 * Set<CellDTO> aliveCells = CellDTO.fromGridPanel(gridPanel);
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
     * Instantiates a new Cell DTO.
     *
     * @param verticalPosition   the vertical position
     * @param horizontalPosition the horizontal position
     */
    public CellDTO(int verticalPosition, int horizontalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
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
     * Extract all alive cells from the given {@link GridPanel} in the form of Cell DTOs.
     *
     * <p>For each alive cell in the grid panel an instance of {@code CellDTO} is created
     * based on its vertical and horizontal position. The {@code CellDTO} instances are
     * collected into a {@code Set}.
     *
     * @param gridPanel the grid panel
     * @return the alive cells
     */
    public static Set<CellDTO> fromGridPanel(GridPanel gridPanel) {
        Set<CellDTO> res = new LinkedHashSet<>();
        for (CellButton cellButton : gridPanel.getAliveCellButtons()) {
            CellDTO cellDTO = new CellDTO();
            cellDTO.setVerticalPosition(cellButton.getVerticalPosition());
            cellDTO.setHorizontalPosition(cellButton.getHorizontalPosition());
            res.add(cellDTO);
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellDTO cellDTO = (CellDTO) o;

        if (verticalPosition != cellDTO.verticalPosition) return false;
        return horizontalPosition == cellDTO.horizontalPosition;
    }

    @Override
    public int hashCode() {
        int result = verticalPosition;
        result = 31 * result + horizontalPosition;
        return result;
    }

    @Override
    public String toString() {
        return "CellDTO{" +
                "verticalPosition=" + verticalPosition +
                ", horizontalPosition=" + horizontalPosition +
                '}';
    }
}
