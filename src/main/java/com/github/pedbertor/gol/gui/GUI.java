package com.github.pedbertor.gol.gui;

import com.github.pedbertor.gol.controller.Controller;
import com.github.pedbertor.gol.controller.ControllerBuilder;
import com.github.pedbertor.gol.gui.component.button.LoadButton;
import com.github.pedbertor.gol.gui.component.button.SaveButton;
import com.github.pedbertor.gol.gui.component.panel.GridPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Game graphical user interface (GUI).
 *
 * <p>Use the {@link #create() create} method to create a {@code GUI} instance
 * and display the graphical user interface of the application:
 *
 * <pre>
 * public static void main(String[] args) {
 *     GUI.create();
 * }
 * </pre>
 *
 * @author Pedro Bernaldez
 */
public class GUI {

    /**
     * The height of the grid in terms of the number of cells vertically.
     */
    public static final int GRID_HEIGHT = 30;
    /**
     * The width of the grid in terms of the number of cells horizontally.
     */
    public static final int GRID_WIDTH = 48;
    /**
     * The panel representing the grid of cells for graphical display.
     */
    public static final GridPanel GRID_PANEL = new GridPanel();
    /**
     * The game controller.
     */
    public static final Controller CONTROLLER = new ControllerBuilder()
            .setGridHeight(GRID_HEIGHT)
            .setGridWidth(GRID_WIDTH)
            .setGameLoopDuration(100)
            .setConsoleOutputEnabled(false)
            .create();

    private static final String DESCRIPTION_LABEL = "Java Implementation of Conway's Game of Life";
    private static final String COPYRIGHT_LABEL = "Copyright (c) 2024 Pedro Bernaldez and others";

    /**
     * Indicates whether the game is currently running.
     */
    public static boolean IS_RUNNING = false;

    private final JButton playButton;
    private final JButton pauseButton;
    private final JButton resetButton;
    private final JButton loadButton;
    private final JButton saveButton;

    private GUI() {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(900, 500);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        playButton = new JButton(new ImageIcon("src/main/resources/icons/play.png"));
        pauseButton = new JButton(new ImageIcon("src/main/resources/icons/pause.png"));
        pauseButton.setEnabled(false);
        resetButton = new JButton(new ImageIcon("src/main/resources/icons/reset.png"));
        loadButton = new LoadButton(mainFrame);
        saveButton = new SaveButton(mainFrame);

        playButton.addActionListener((event) -> {
            if (!IS_RUNNING) {
                CONTROLLER.start();
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
                resetButton.setEnabled(false);
                loadButton.setEnabled(false);
                saveButton.setEnabled(false);
                IS_RUNNING = true;
            }
        });
        pauseButton.addActionListener((event) -> {
            if (IS_RUNNING) {
                CONTROLLER.stop();
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
                resetButton.setEnabled(true);
                loadButton.setEnabled(true);
                saveButton.setEnabled(true);
                IS_RUNNING = false;
            }
        });
        resetButton.addActionListener((event) -> {
            if (!IS_RUNNING) {
                GRID_PANEL.reset();
            }
        });

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3));
        optionsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        optionsPanel.add(generateLeftPartOfOptionsPanel(), BorderLayout.WEST);
        optionsPanel.add(generateCentralPartOfOptionsPanel(), BorderLayout.CENTER);
        optionsPanel.add(generateRightPartOfOptionsPanel(), BorderLayout.EAST);

        mainFrame.add(GRID_PANEL, BorderLayout.CENTER);
        mainFrame.add(optionsPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private JPanel generateLeftPartOfOptionsPanel() {
        JPanel leftPartPanel = new JPanel();
        leftPartPanel.setLayout(new BoxLayout(leftPartPanel, BoxLayout.Y_AXIS));

        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText(DESCRIPTION_LABEL);
        descriptionLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        JLabel copyrightLabel = new JLabel();
        copyrightLabel.setText(COPYRIGHT_LABEL);
        copyrightLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));

        leftPartPanel.add(descriptionLabel);
        leftPartPanel.add(copyrightLabel);

        return leftPartPanel;
    }

    private JPanel generateCentralPartOfOptionsPanel() {
        JPanel centralPartPanel = new JPanel();
        centralPartPanel.setLayout(new BoxLayout(centralPartPanel, BoxLayout.X_AXIS));

        centralPartPanel.add(Box.createHorizontalGlue());

        centralPartPanel.add(playButton);
        centralPartPanel.add(Box.createHorizontalStrut(10));
        centralPartPanel.add(pauseButton);
        centralPartPanel.add(Box.createHorizontalStrut(10));
        centralPartPanel.add(resetButton);

        centralPartPanel.add(Box.createHorizontalGlue());

        return centralPartPanel;
    }

    private JPanel generateRightPartOfOptionsPanel() {
        JPanel rightPartPanel = new JPanel();
        rightPartPanel.setLayout(new BorderLayout());

        JPanel rightPanelContainer = new JPanel();
        rightPanelContainer.setLayout(new BoxLayout(rightPanelContainer, BoxLayout.X_AXIS));
        rightPanelContainer.add(loadButton);
        rightPanelContainer.add(Box.createHorizontalStrut(10));
        rightPanelContainer.add(saveButton);

        rightPartPanel.add(rightPanelContainer, BorderLayout.EAST);

        return rightPartPanel;
    }

    /**
     * Creates a {@code GUI} instance to display the graphical user interface
     * of the application.
     */
    public static void create() {
        new GUI();
    }

    /**
     * Switches the state of the cell button located in the given position.
     *
     * @param verticalPosition   the vertical position
     * @param horizontalPosition the horizontal position
     */
    public static void switchCellButtonState(int verticalPosition, int horizontalPosition) {
        GRID_PANEL.getCellButton(verticalPosition, horizontalPosition).switchState();
    }
}
