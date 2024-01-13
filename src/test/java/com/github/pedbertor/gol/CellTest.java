package com.github.pedbertor.gol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link Cell}.
 *
 * @author Pedro Bernaldez
 */
public class CellTest {

    private Cell cell;

    @BeforeEach
    public void init() {
        cell = new Cell();
    }

    @Test
    public void checkSwitchState() {
        assertFalse(cell.isAlive());
        cell.switchState();
        assertTrue(cell.isAlive());
        cell.switchState();
        assertFalse(cell.isAlive());
    }

    @Test
    public void checkCellDoesNotReviveWithTwoAliveNeighbors() {
        assertFalse(cell.isAlive());
        updateStateWith(2);
        assertFalse(cell.isAlive());
    }

    @Test
    public void checkCellDoesNotReviveWithFourAliveNeighbors() {
        assertFalse(cell.isAlive());
        updateStateWith(4);
        assertFalse(cell.isAlive());
    }

    @Test
    public void checkCellRevivesWithThreeAliveNeighbors() {
        assertFalse(cell.isAlive());
        updateStateWith(3);
        assertTrue(cell.isAlive());
    }

    @Test
    public void checkCellDoesNotDieWithTwoAliveNeighbors() {
        cell.switchState();
        assertTrue(cell.isAlive());
        updateStateWith(2);
        assertTrue(cell.isAlive());
    }

    @Test
    public void checkCellDoesNotDieWithThreeAliveNeighbors() {
        cell.switchState();
        assertTrue(cell.isAlive());
        updateStateWith(3);
        assertTrue(cell.isAlive());
    }

    @Test
    public void checkCellDiesWithOneAliveNeighbor() {
        cell.switchState();
        assertTrue(cell.isAlive());
        updateStateWith(1);
        assertFalse(cell.isAlive());
    }

    @Test
    public void checkCellDiesWithFourAliveNeighbors() {
        cell.switchState();
        assertTrue(cell.isAlive());
        updateStateWith(4);
        assertFalse(cell.isAlive());
    }

    private void updateStateWith(int aliveNeighbors) {
        cell.setNeighbors(generateNeighbors(aliveNeighbors));
        cell.calculateAliveNeighbors();
        cell.updateStateBasedOnNeighbors();
    }

    private static Set<Cell> generateNeighbors(int aliveNeighbors) {
        Set<Cell> res = new LinkedHashSet<>();
        // Generate alive neighbors (if any)
        for (int i = 0; i < aliveNeighbors; i++) {
            Cell aliveNeighbor = new Cell();
            aliveNeighbor.switchState();
            res.add(aliveNeighbor);
        }
        // Generate dead neighbors (if any)
        for (int i = 0; i < (8 - aliveNeighbors); i++) {
            Cell deadNeighbor = new Cell();
            res.add(deadNeighbor);
        }
        return res;
    }
}
