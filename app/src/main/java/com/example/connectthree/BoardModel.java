package com.example.connectthree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ross on 12/12/2017.
 */

public class BoardModel implements BoardModelInterface {
    List<BoardObserver> boardObserverList = new ArrayList<BoardObserver>();
    List<Cell> cellList = new ArrayList<Cell>();
    boolean hasWon;
    boolean redsTurn;

    BoardModel() {
        initialiseBoard();
    }

    public void setCell(int pos) {
        if (isRedsTurn()) {
            cellList.get(pos).setState(CellState.RED);
            setRedsTurn(false);
        }
        else {
            cellList.get(pos).setState(CellState.YELLOW);
            setRedsTurn(true);
        }
    }

    public boolean hasWon() {

        return hasWon;
    }

    public void registerBoardObserver(BoardObserver boardObserver) {
        boardObserverList.add(boardObserver);
    }

    public void removeBoardObserver(BoardObserver boardObserver) {
        int i = boardObserverList.indexOf(boardObserver);
        boardObserverList.remove(i);
    }

    private void initialiseBoard() {
        for (int i = 0; i < 9; ++i) {
            cellList.add(new Cell());
        }
    }

    public boolean isRedsTurn() {
        return redsTurn;
    }

    public void setRedsTurn(boolean redsTurn) {
        this.redsTurn = redsTurn;
    }

    @Override
    public String toString() {
        String boardObserverString;
        String cellStrings = "";
        String boardString = "";

        boardObserverString = boardObserverList.toString();

        for (Cell cell : cellList) {
            cellStrings += cell.toString() + ",";
        }

        boardString += boardObserverString + cellStrings + hasWon;

        return boardString;
    }
}
