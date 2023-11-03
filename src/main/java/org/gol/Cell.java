package org.gol;

public class Cell {

    private boolean isAlive = false;

    public Cell() {
        super();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void changeState() {
        isAlive = !isAlive;
    }
}
