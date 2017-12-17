package com.example.connectthree;

/**
 * Created by Ross on 16/12/2017.
 */

public class PositionAlreadySetException extends Exception {
    PositionAlreadySetException(){
        super("Counter already present!");
    }
}
