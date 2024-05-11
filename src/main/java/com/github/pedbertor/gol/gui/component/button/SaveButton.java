package com.github.pedbertor.gol.gui.component.button;

import com.github.pedbertor.gol.gui.GUI;
import com.github.pedbertor.gol.gui.component.filechooser.GridFileChooser;
import com.github.pedbertor.gol.util.GridPanelUtils;
import org.apache.commons.io.FilenameUtils;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;

/**
 * Button component for saving grids.
 *
 * @author Pedro Bernaldez
 */
public class SaveButton extends JButton {

    private static final String TEXT = "Save";
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 10);

    private final JFrame frame;
    private final GridFileChooser fileChooser;

    /**
     * Instantiates a new Save Button.
     *
     * @param frame the frame
     */
    public SaveButton(JFrame frame) {
        super();

        this.frame = frame;

        fileChooser = new GridFileChooser(TEXT);

        setText(TEXT);
        setFont(FONT);
        addActionListener(setUpSaveActionListener());
    }

    private ActionListener setUpSaveActionListener() {
        return (event) -> {
            int action = fileChooser.showSaveDialog(frame);
            if (action == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                if (!FilenameUtils.isExtension(fileName, "json")) {
                    fileName += ".json";
                }
                try {
                    if (!GUI.GRID_PANEL.getAliveCellButtons().isEmpty()) {
                        GridPanelUtils.saveStateToFile(GUI.GRID_PANEL, fileName);
                    } else {
                        showSavingErrorDialog("There are no alive cells in the grid");
                    }
                } catch (Exception e) {
                    showSavingErrorDialog("An error occurred while trying to save");
                }
            }
        };
    }

    private void showSavingErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Saving Error", JOptionPane.ERROR_MESSAGE);
    }
}
