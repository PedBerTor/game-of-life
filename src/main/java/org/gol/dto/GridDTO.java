package org.gol.dto;

import org.gol.Grid;

import java.util.Set;

public class GridDTO {

    private int height;
    private int width;
    private Set<CellDTO> aliveCells;

    public GridDTO() {
        super();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Set<CellDTO> getAliveCells() {
        return aliveCells;
    }

    public void setAliveCells(Set<CellDTO> aliveCells) {
        this.aliveCells = aliveCells;
    }

    public static Grid toGrid(GridDTO gridDTO) {
        Grid grid = new Grid(gridDTO.getHeight(), gridDTO.getWidth());
        for (CellDTO cellDTO : gridDTO.getAliveCells()) {
            grid.getCell(cellDTO.getVerticalPosition(), cellDTO.getHorizontalPosition()).switchState();
        }
        return grid;
    }

    public static GridDTO fromGrid(Grid grid) {
        GridDTO res = new GridDTO();
        res.setHeight(grid.getHeight());
        res.setWidth(grid.getWidth());
        res.setAliveCells(CellDTO.fromGrid(grid));
        return res;
    }
}
