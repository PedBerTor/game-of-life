package com.github.pedbertor.gol.dto;

import com.github.pedbertor.gol.gui.GUI;
import com.github.pedbertor.gol.gui.component.panel.GridPanel;

import java.util.Objects;
import java.util.Set;

/**
 * Grid DTO for {@link GridPanel} serialization/deserialization.
 *
 * <p>A {@code GridDTO} instance represents the state of a {@link GridPanel} at a particular
 * iteration, which comprises its size (height and width) and the position of all alive
 * cells in it.
 *
 * <p>Use the {@link #fromGridPanel(GridPanel) fromGridPanel} method to get a {@code GridDTO}
 * instance from a {@link GridPanel} instance:
 *
 * <pre>
 * GridDTO gridDTO = GridDTO.fromGridPanel(gridPanel);
 * </pre>
 *
 * @author Jo Chong Min
 */
public class GridDTO {

    private int height;
    private int width;
    private Set<CellDTO> aliveCells;

    /**
     * Instantiates a new Grid DTO.
     */
    public GridDTO() {
        super();
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets alive cells.
     *
     * @return the alive cells
     */
    public Set<CellDTO> getAliveCells() {
        return aliveCells;
    }

    /**
     * Sets alive cells.
     *
     * @param aliveCells the alive cells
     */
    public void setAliveCells(Set<CellDTO> aliveCells) {
        this.aliveCells = aliveCells;
    }

    /**
     * Gets a {@code GridDTO} from the given {@link GridPanel}.
     *
     * @param gridPanel the grid panel
     * @return the grid DTO
     */
    public static GridDTO fromGridPanel(GridPanel gridPanel) {
        GridDTO res = new GridDTO();
        res.setHeight(GUI.GRID_HEIGHT);
        res.setWidth(GUI.GRID_WIDTH);
        res.setAliveCells(CellDTO.fromGridPanel(gridPanel));
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridDTO gridDTO = (GridDTO) o;

        if (height != gridDTO.height) return false;
        if (width != gridDTO.width) return false;
        return Objects.equals(aliveCells, gridDTO.aliveCells);
    }

    @Override
    public int hashCode() {
        int result = height;
        result = 31 * result + width;
        result = 31 * result + (aliveCells != null ? aliveCells.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GridDTO{" +
                "height=" + height +
                ", width=" + width +
                ", aliveCells=" + aliveCells +
                '}';
    }
}
