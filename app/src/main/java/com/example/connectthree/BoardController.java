package com.example.connectthree;

/**
 * Created by Ross on 12/12/2017.
 */

public class BoardController implements BoardControllerInterface {
    BoardModelInterface boardModel;

    BoardController(BoardModelInterface boardModel) {
        this.boardModel = boardModel;
    }

    public void dropCounter(int pos) {
        boardModel.setCell(pos);
    }

    public boolean isRedsTurn() {
        return boardModel.isRedsTurn();
    }
}
