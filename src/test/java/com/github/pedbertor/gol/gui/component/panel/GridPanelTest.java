package com.github.pedbertor.gol.gui.component.panel;

import org.junit.jupiter.api.Test;

import java.awt.GridLayout;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link GridPanel}.
 *
 * @author Pedro Bernaldez
 */
public class GridPanelTest {

    private static final int GRID_HEIGHT = 30;
    private static final int GRID_WIDTH = 48;

    private final GridPanel gridPanel = new GridPanel();

    @Test
    public void checkGridSize() {
        GridLayout layout = (GridLayout) gridPanel.getLayout();
        assertEquals(GRID_HEIGHT, layout.getRows());
        assertEquals(GRID_WIDTH, layout.getColumns());
    }

    @Test
    public void checkReset() {
        assertEquals(0, gridPanel.getAliveCellButtons().size());
        gridPanel.getCellButton(0, 0).switchState();
        gridPanel.getCellButton(1, 0).switchState();
        gridPanel.getCellButton(0, 1).switchState();
        assertEquals(3, gridPanel.getAliveCellButtons().size());
        gridPanel.reset();
        assertEquals(0, gridPanel.getAliveCellButtons().size());
    }
}
