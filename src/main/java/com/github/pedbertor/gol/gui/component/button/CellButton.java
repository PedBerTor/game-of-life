package com.github.pedbertor.gol.gui.component.button;

import com.github.pedbertor.gol.gui.GUI;

import javax.swing.JButton;
import java.awt.Color;

/**
 * Button component that represents a cell.
 *
 * @author Pedro Bernaldez
 */
public class CellButton extends JButton {

    private static final Color ALIVE_COLOR = Color.black;
    private static final Color DEAD_COLOR = Color.white;

    private final int verticalPosition;
    private final int horizontalPosition;

    private boolean isAlive;

    /**
     * Instantiates a new Cell Button.
     *
     * @param verticalPosition   the vertical position
     * @param horizontalPosition the horizontal position
     */
    public CellButton(int verticalPosition, int horizontalPosition) {
        super();

        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;

        setFocusPainted(false);
        setBorderPainted(false);
        setBackground(DEAD_COLOR);
        addActionListener((event) -> switchStateOnClickOrLoad());
    }

    /**
     * Gets vertical position.
     *
     * @return the vertical position
     */
    public int getVerticalPosition() {
        return verticalPosition;
    }

    /**
     * Gets horizontal position.
     *
     * @return the horizontal position
     */
    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    /**
     * Checks whether the cell button is alive.
     *
     * @return true if the cell button is alive; false otherwise
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Switches the state of the cell button between alive and dead
     * externally, updating its appearance accordingly.
     */
    public void switchState() {
        isAlive = !isAlive;
        setBackground(isAlive ? ALIVE_COLOR : DEAD_COLOR);
    }

    /**
     * Switches the state of the cell button between alive and dead,
     * both externally, updating its appearance accordingly, and
     * internally.
     */
    public void switchStateOnClickOrLoad() {
        if (!GUI.IS_RUNNING) {
            GUI.CONTROLLER.switchCellState(verticalPosition, horizontalPosition);
            isAlive = !isAlive;
            setBackground(isAlive ? ALIVE_COLOR : DEAD_COLOR);
        }
    }

    /**
     * Sets the state of the cell button to dead externally, updating
     * its appearance accordingly.
     */
    public void kill() {
        isAlive = false;
        setBackground(DEAD_COLOR);
    }
}
