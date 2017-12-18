package com.example.connectthree;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
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
    List<ImageView> imageViews = new ArrayList<ImageView>();                                    /* A nice list of imageViews */
    GridLayout layout;                                                                  /* We need to reference our gridlayout */


    public void clickCell(View view) {
        Log.i("Method called:", "clickCell");
        ImageView imageView = (ImageView)view;
        Log.i("Info", "Got reference to imageView that was clicked.");


        int pos = indexOfImageViews(imageView);
        Log.i("Info", "Position (pos): " + pos);

        // get the appropriate position
        pos = boardModel.getSuitablePosition(pos);
        Log.i("Info", "Appropriate Position (pos):" + pos);

        //get appropriate child view
        imageView = (ImageView)layout.getChildAt(pos);

        try {
            boardController.dropCounter(pos);
            if (!boardController.isRedsTurn()) {
                imageView.setImageResource(R.drawable.yellow);
            }

            imageView.setTranslationY(-2000);
            imageView.setAlpha(1f);
            imageView.animate().translationYBy(2000).setDuration(800);
            Log.i("Info", "A user has won: " + boardModel.hasWon());

            List<Integer> winningSequence = boardModel.getWinningSequence();

            for (int i = 0; boardModel.hasWon() && i < 5; ++i) {
                flashWinningSequence(winningSequence);
                Log.i("Info","Flash Flash Flash");
                endOfGame();
            }
        }
        catch (PositionAlreadySetException e) {
            Log.i("Error", e.getMessage());
        }

        Log.i("Board Model: ", boardModel.toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.gridLayoutBoard);
        Log.i("Info", "Got reference to GridLayout");
        initialiseImageViewList();
        Log.i("Info", "Initialized imageViewList");
    }

    private void initialiseImageViewList() {
        int i = 0;

        int count = layout.getChildCount();
        Log.i("Info", "Child Count for GridLayout is: " + count);

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

    private void flashWinningSequence(List<Integer> winningSequence) {
        ImageView counter;

        Log.i("Info", "Winning Sequence is: ");

        for (int i = 0; i < winningSequence.size(); ++i) {
            counter = (ImageView) layout.getChildAt(winningSequence.get(i));
            Animation animation = new AlphaAnimation(1, 0);
            animation.setStartOffset(800);
            animation.setDuration(200);
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(6);
            animation.setRepeatMode(Animation.REVERSE);
            counter.startAnimation(animation);
        }
    }

    private void endOfGame() {
        for (ImageView imageView : imageViews) {
            imageView.setClickable(false );
        }
    }
}
