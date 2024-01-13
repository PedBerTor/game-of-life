package com.github.pedbertor.gol.dto;

import com.github.pedbertor.gol.Grid;

import java.util.Set;

/**
 * Grid DTO for {@link Grid} serialization/deserialization.
 *
 * <p>A {@code GridDTO} instance represents the state of a {@link Grid} at a particular
 * iteration, which comprises its size (height and width) and the position of all alive
 * cells in it.
 *
 * <p>Use the {@link #toGrid(GridDTO) toGrid} method to get a {@link Grid} instance
 * from a {@code GridDTO}:
 *
 * <pre>
 * Grid grid = GridDTO.toGrid(gridDTO);
 * </pre>
 *
 * <p>Use the {@link #fromGrid(Grid) fromGrid} method to get a {@code GridDTO} instance
 * from a {@link Grid}:
 *
 * <pre>
 * GridDTO gridDTO = GridDTO.fromGrid(grid);
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
     * Gets a {@link Grid} from the given {@code GridDTO}.
     *
     * @param gridDTO the grid DTO
     * @return the grid
     */
    public static Grid toGrid(GridDTO gridDTO) {
        Grid grid = new Grid(gridDTO.getHeight(), gridDTO.getWidth());
        for (CellDTO cellDTO : gridDTO.getAliveCells()) {
            grid.getCell(cellDTO.getVerticalPosition(), cellDTO.getHorizontalPosition()).switchState();
        }
        return grid;
    }

    /**
     * Gets a {@code GridDTO} from the given {@link Grid}.
     *
     * @param grid the grid
     * @return the grid DTO
     */
    public static GridDTO fromGrid(Grid grid) {
        GridDTO res = new GridDTO();
        res.setHeight(grid.getHeight());
        res.setWidth(grid.getWidth());
        res.setAliveCells(CellDTO.fromGrid(grid));
        return res;
    }
}
