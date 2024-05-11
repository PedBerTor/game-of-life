package com.github.pedbertor.gol.gui.component.button;

import org.junit.jupiter.api.Test;

import java.awt.Font;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link LoadButton}.
 *
 * @author Pedro Bernaldez
 */
public class LoadButtonTest {

    private static final String TEXT = "Load";
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 10);

    private final LoadButton loadButton = new LoadButton(null);

    @Test
    public void checkText() {
        assertEquals(TEXT, loadButton.getText());
    }

    @Test
    public void checkFont() {
        assertEquals(FONT, loadButton.getFont());
    }
}
