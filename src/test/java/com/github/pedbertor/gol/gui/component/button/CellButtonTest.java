package com.github.pedbertor.gol.gui.component.button;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link CellButton}.
 *
 * @author Pedro Bernaldez
 */
public class CellButtonTest {

    private static final Color ALIVE_COLOR = Color.black;
    private static final Color DEAD_COLOR = Color.white;

    private final CellButton cellButton = new CellButton(0, 0);

    @Test
    public void checkSwitchState() {
        assertFalse(cellButton.isAlive());
        assertEquals(DEAD_COLOR, cellButton.getBackground());
        cellButton.switchState();
        assertTrue(cellButton.isAlive());
        assertEquals(ALIVE_COLOR, cellButton.getBackground());
    }
}
