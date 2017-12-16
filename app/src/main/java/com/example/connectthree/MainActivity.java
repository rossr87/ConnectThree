package com.example.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Let this be the View.

Ok, Red goes first. We'll refactor later to change this!
 */

public class MainActivity extends AppCompatActivity {
    BoardModelInterface boardModel = new BoardModel();                                  /* Reference to the Model */
    BoardControllerInterface boardController = new BoardController(boardModel);         /* Reference to the Controller */
    List<ImageView> imageViews = new ArrayList<ImageView>();                            /* A nice list of imageViews */
    GridLayout layout;                                                                  /* We need to reference our gridlayout */


    public void clickCell(View view) {
        Log.i("Method called:", "clickCell");
        ImageView imageView = (ImageView)view;
        Log.i("Info", "Got reference to imageView that was clicked.");


        int pos = indexOfImageViews(imageView);
        Log.i("Info", "Position (pos): " + pos);

        /*
        Request to the controller, to change the state of the board.
        The View doesn't really need to be aware of the colour of the Counter in terms, of the request
        send to the controller.
         */
        boardController.dropCounter(pos);
        Log.i("Board Model: ", boardModel.toString());

        /*
        Set Red or Yellow accordingly.
         */
        if (boardController.isRedsTurn()) {
            imageView.setImageResource(R.drawable.yellow);
        }

        imageView.setTranslationY(-2000);
        imageView.setAlpha(1f);

        imageView.animate().translationYBy(2000).setDuration(1500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        We'll create a list of image views.
         */
        layout = findViewById(R.id.gridLayoutBoard);
        Log.i("Info", "Got reference to GridLayout");
        initialiseImageViewList();
        Log.i("Info", "Initialized imageViewList");
    }

    private void initialiseImageViewList() {
        int i = 0;

        /*
        Get the number of child views from the parent view - the GridLayout.
         */
        int count = layout.getChildCount();

        Log.i("Info", "Child Count for GridLayout is: " + count);

        /*
        Generate a list of references to each child view that acts
        as a "cell" on the board.
         */
        for (; i < 9; ++i) {
            imageViews.add((ImageView) layout.getChildAt(i));
        }
    }

    private int indexOfImageViews(ImageView counter) {
        int i = 0;

        for (; counter != imageViews.get(i); ++i) {
            ;
        }

        return i;
    }
}
