package com.example.connectthree;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ross on 12/12/2017.
 */

public class BoardModel implements BoardModelInterface {
    List<BoardObserver> boardObserverList = new ArrayList<BoardObserver>();
    List<Cell> cellList = new ArrayList<Cell>();
    List<Integer> winningSequence = new ArrayList<Integer>();
    boolean hasWon;
    boolean redsTurn;

    BoardModel() {
        initialiseBoard();
    }

    public void setCell(int pos) throws PositionAlreadySetException {

        if (isRedsTurn() && (cellList.get(pos).getState() == CellState.EMPTY)) {
            cellList.get(pos).setState(CellState.RED);
            setRedsTurn(false);
        }
        else if (!isRedsTurn() && (cellList.get(pos).getState() == CellState.EMPTY)) {
            cellList.get(pos).setState(CellState.YELLOW);
            setRedsTurn(true);
        }
        else {
            throw new PositionAlreadySetException();
        }
    }

    public List<Integer> getWinningSequence() {
        return winningSequence;
    }

    private void setWinningSequence(final int a, final int b, final int c) {
        winningSequence.add(a);
        winningSequence.add(b);
        winningSequence.add(c);
    }

    public boolean hasWon() {
        return (checkHorizontalWin() ||
                checkVerticalWin() ||
                checkDiagonalWin());
    }

    private boolean checkHorizontalWin() {
        boolean won = false;

        for (int i = 0; i <= 6 ; i +=3) {
                if (
                        (cellList.get(i).getState() != CellState.EMPTY) &&
                        (cellList.get(i).getState() == cellList.get(i+1).getState()) &&
                        (cellList.get(i).getState() == cellList.get(i+2).getState())
                        ) {
                    won = true;
                    setWinningSequence(i, i+1, i+2);
                    break;
                }
        }
        return won;
    }

    private boolean checkVerticalWin() {
        boolean won = false;

        for (int i = 0; i <= 2; ++i) {
            if(
                    (cellList.get(i).getState() != CellState.EMPTY) &&
                    (cellList.get(i).getState() == cellList.get(i+3).getState()) &&
                    (cellList.get(i).getState() == cellList.get(i+6).getState())
                    ) {
                won = true;
                setWinningSequence(i, i+3, i+6);
                break;
            }
        }
        return won;
    }

    private boolean checkDiagonalWin() {
        boolean won = false;
        int i = 0;

        if (
                                    (cellList.get(i).getState() != CellState.EMPTY) &&
                                    (cellList.get(i).getState() == cellList.get(i + 4).getState()) &&
                                    (cellList.get(i).getState() == cellList.get(i + 8).getState())
                ) {
                won = true;
                setWinningSequence(i, i+4, i+8);
        }

        i = 2;
        if (
                        (cellList.get(i).getState() != CellState.EMPTY) &&
                        (cellList.get(i).getState() == cellList.get(i + 2).getState()) &&
                        (cellList.get(i).getState() == cellList.get(i + 4).getState())
                ) {
             won = true;
            setWinningSequence(i, i+2, i+4);
        }

        return won;
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

    /*
     * Instead of the counter dropping in to the view that
     * the user selects, this routine finds the next available
     * position in the column.
     * When a player taps on a view in a given column, the next available
     * view from the column is selected.
     */
    public int getSuitablePosition(int pos) {
        int maxPosForColumn = 0;
        int i = 0;

        if ((pos % 3) == 0) {
            maxPosForColumn = 6;
        } else if ((pos % 3) == 1) {
            maxPosForColumn = 7;
        } else if ((pos % 3) == 2) {
            maxPosForColumn = 8;
        } else {
            Log.e("Error", "Invalid position: " + pos);
        }
        for (i = maxPosForColumn; i >= pos; i -=3) {
            if (cellList.get(i).getState() == CellState.EMPTY) {
                break;
            }
        }

        if (i < 0) {
            i = pos;
        }

        return i;
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
