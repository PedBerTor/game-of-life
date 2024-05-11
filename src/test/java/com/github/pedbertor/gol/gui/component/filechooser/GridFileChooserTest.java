package com.github.pedbertor.gol.gui.component.filechooser;

import org.junit.jupiter.api.Test;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link GridFileChooser}.
 *
 * @author Pedro Bernaldez
 */
public class GridFileChooserTest {

    private static final String TITLE = "Grid File Chooser";
    private static final String GRIDS_DIR = "./src/main/resources/grids";
    private static final FileFilter FILE_FILTER = new FileNameExtensionFilter("JSON files", "json");
    private static final File DIR_TO_LOCK = new File(GRIDS_DIR);

    private final GridFileChooser gridFileChooser = new GridFileChooser(TITLE);

    @Test
    public void checkTitle() {
        assertEquals(TITLE, gridFileChooser.getDialogTitle());
    }

    @Test
    public void checkFileFilter() {
        assertFalse(gridFileChooser.isAcceptAllFileFilterUsed());
        assertEquals(FILE_FILTER.getDescription(), gridFileChooser.getFileFilter().getDescription());
    }

    @Test
    public void checkFileView() {
        assertTrue(gridFileChooser.getFileView().isTraversable(DIR_TO_LOCK));
    }
}
