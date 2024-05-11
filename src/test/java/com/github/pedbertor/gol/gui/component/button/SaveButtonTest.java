package com.github.pedbertor.gol.gui.component.button;

import org.junit.jupiter.api.Test;

import java.awt.Font;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link SaveButton}.
 *
 * @author Pedro Bernaldez
 */
public class SaveButtonTest {

    private static final String TEXT = "Save";
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 10);

    private final SaveButton saveButton = new SaveButton(null);

    @Test
    public void checkText() {
        assertEquals(TEXT, saveButton.getText());
    }

    @Test
    public void checkFont() {
        assertEquals(FONT, saveButton.getFont());
    }
}
