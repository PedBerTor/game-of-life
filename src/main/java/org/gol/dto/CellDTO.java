package org.gol.dto;

import org.gol.Grid;

import java.util.LinkedHashSet;
import java.util.Set;

public class CellDTO {

    private int verticalPosition;
    private int horizontalPosition;

    public CellDTO() {
        super();
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

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
