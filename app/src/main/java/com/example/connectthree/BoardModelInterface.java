package com.example.connectthree;

/**
 * Created by Ross on 12/12/2017.
 *
 * Encapsulates the logic and the state of the game.
 */

public interface BoardModelInterface {
    /*
    What does the Controller need to do to the Model?
    It needs to:
    - Indicate to the model that a player wants to drop a counter.
    - Check whether the game has been won
     */
    public void setCell(int pos);                /* called each time a player takes a turn */
    public boolean hasWon();                     /* called to check whether a player has won */

    /*
    The observer pattern part.
    The View registers with the Board as an Observer.
    When a change in the board state occurs, the view will be notified.
     */

    public void registerBoardObserver(BoardObserver boardObserver);
    public void removeBoardObserver(BoardObserver boardObserver);

    public boolean isRedsTurn();
    public void setRedsTurn(boolean redsTurn);

}
