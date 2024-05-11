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
 * Button component for loading grids.
 *
 * @author Pedro Bernaldez
 */
public class LoadButton extends JButton {

    private static final String TEXT = "Load";
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 10);

    private final JFrame frame;
    private final GridFileChooser fileChooser;

    /**
     * Instantiates a new Load Button.
     *
     * @param frame the frame
     */
    public LoadButton(JFrame frame) {
        super();

        this.frame = frame;

        fileChooser = new GridFileChooser(TEXT);

        setText(TEXT);
        setFont(FONT);
        addActionListener(setUpLoadActionListener());
    }

    private ActionListener setUpLoadActionListener() {
        return (event) -> {
            int action = fileChooser.showOpenDialog(frame);
            if (action == JFileChooser.APPROVE_OPTION) {
                String chosenFile = fileChooser.getSelectedFile().getName();
                if (!FilenameUtils.getExtension(chosenFile).equalsIgnoreCase("json")) {
                    showLoadingErrorDialog("The chosen file extension is not supported");
                } else {
                    try {
                        GridPanelUtils.loadStateFromFile(GUI.GRID_PANEL, chosenFile);
                    } catch (Exception e) {
                        showLoadingErrorDialog("An error occurred while trying to load");
                    }
                }
            }
        };
    }

    private void showLoadingErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Loading Error", JOptionPane.ERROR_MESSAGE);
    }
}
