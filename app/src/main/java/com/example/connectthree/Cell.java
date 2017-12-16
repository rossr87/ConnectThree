package com.example.connectthree;

/**
 * Created by Ross on 12/12/2017.
 */

/*
Represents an individual cell.
 */
class Cell {
    CellState state = CellState.EMPTY;

    public void setState(CellState state) {
        this.state = state;
    }

    /*
    case CellState.EMPTY !! NO !!
    An enum switch case label must be the unqualified name of an enumeration constant
     */
    @Override
    public String toString() {
        String stateString;

        switch (this.state) {
            case EMPTY:
                stateString = "EMPTY";
                break;
            case RED:
                stateString = "RED";
                break;
            case YELLOW:
                stateString = "YELLOW";
                break;
            default:
                stateString = "INVALID";
                break;
        }
        return stateString;
    }
}
