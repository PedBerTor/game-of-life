package org.gol.dto;

import org.gol.Grid;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class GridDTO {
    private int height;
    private int width;
    private Set<CellDTO> aliveCells;

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
        GridDTO gridDTO = new GridDTO();
        gridDTO.setHeight(grid.getHeight());
        gridDTO.setWidth(grid.getWidth());
        Set<CellDTO> aliveCells = new LinkedHashSet<>();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.getCell(i, j).isAlive()) {
                    CellDTO cellDTO = new CellDTO();
                    cellDTO.setVerticalPosition(i);
                    cellDTO.setHorizontalPosition(j);
                    aliveCells.add(cellDTO);
                }
            }
        }
        gridDTO.setAliveCells(aliveCells);
        return gridDTO;
    }
}
