package com.github.pedbertor.gol.gui.component.filechooser;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * File Chooser component for grids.
 *
 * @author Pedro Bernaldez
 */
public class GridFileChooser extends JFileChooser {

    private static final String GRIDS_DIR = "./src/main/resources/grids";
    private static final FileFilter FILE_FILTER = new FileNameExtensionFilter("JSON files", "json");
    private static final File DIR_TO_LOCK = new File(GRIDS_DIR);

    /**
     * Instantiates a new Grid File Chooser.
     *
     * @param dialogTitle the dialog title
     */
    public GridFileChooser(String dialogTitle) {
        super(GRIDS_DIR);

        setDialogTitle(dialogTitle);
        setAcceptAllFileFilterUsed(false);
        setFileFilter(FILE_FILTER);
        setFileView(new FileView() {
            @Override
            public Boolean isTraversable(File f) {
                return DIR_TO_LOCK.equals(f);
            }
        });
    }
}
