package com.example.connectthree;

/**
 * Created by Ross on 12/12/2017.
 */

public interface BoardControllerInterface {
    public void dropCounter(int pos) throws PositionAlreadySetException;
    public boolean isRedsTurn();
}
