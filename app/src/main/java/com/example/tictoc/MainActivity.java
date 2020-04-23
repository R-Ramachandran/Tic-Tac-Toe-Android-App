package com.example.tictoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI(findViewById(R.id.constraintLayout3));

        ConstraintLayout conLay = findViewById(R.id.constraintLayout1);
        AnimationDrawable animationDrawable = (AnimationDrawable) conLay.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }

    public void submitPlayers(View view) {
        LinearLayout linearLayout_14 = findViewById(R.id.linearLayout14);
        EditText player1 = findViewById(R.id.editText3), player2 = findViewById(R.id.editText2);
        String p1_name = player1.getText().toString(), p2_name = player2.getText().toString();
        TextView textView_5 = findViewById(R.id.textView20), textView_7 = findViewById(R.id.textView21), textView_ = findViewById(R.id.textView), textView_3 = findViewById(R.id.textView3);
        if (p1_name.equals("") || p2_name.equals("") || p1_name.equals(" ") || p2_name.equals(" ")) {
            Toast.makeText(this, "Check Players Name!", Toast.LENGTH_SHORT).show();
        }
        else if(p1_name.equals(p2_name)) {
            Toast.makeText(this, "Player names Can't be Same!", Toast.LENGTH_SHORT).show();
        }
        else {
            linearLayout_14.animate().alpha(0).setDuration(2000);
            linearLayout_14.setVisibility(View.INVISIBLE);
            textView_5.setText(p1_name);
            textView_7.setText(p2_name);
            textView_.setText(p1_name);
            textView_3.setText(p2_name);
            this.player1Is = p1_name;
            this.player2Is = p2_name;
        }
    }

    private String player1Is, player2Is;
    private boolean isPlayer1X = true, isX = true;
    private int counter=0, whoWon = -1, player1Points = 0, player2Points = 0;

    public void fixXO(View view) {
        Spinner player1Option = findViewById(R.id.spinner), player2Option = findViewById(R.id.spinner2);
        String player1XOrO = player1Option.getSelectedItem().toString();
        String player2XOrO = player2Option.getSelectedItem().toString();
        if (player1XOrO.equals(player2XOrO)) {
            Toast.makeText(this, "Selection should be Unique!", Toast.LENGTH_SHORT).show();
        }
        else {
            LinearLayout l5 = findViewById(R.id.linearLayout5);
            l5.setVisibility(View.INVISIBLE);
            LinearLayout l2 = findViewById(R.id.linearLayout2);
            l2.animate().alpha(1).setDuration(2000);
            GridLayout g1 = findViewById(R.id.gridLayout);
            g1.animate().alpha(1).setDuration(2000);
            GridLayout g2 = findViewById(R.id.gridLayout2);
            g2.animate().alpha(1).setDuration(2000);
            LinearLayout l1 = findViewById(R.id.linearLayout1);
            l1.animate().alpha(1).setDuration(2000);
            if (player1XOrO.equals("O")) {
                this.isPlayer1X = false;
            }
        }
    }

    public void putXO(View view) {
        int imageID = view.getId();
        ImageView _imageView = findViewById(imageID);
        if (this.isX == true) {
            _imageView.setImageResource(R.drawable.x);
            _imageView.setTag("x");
            this.isX = false;
            check();
        }
        else {
            _imageView.setImageResource(R.drawable.o);
            _imageView.setTag("o");
            this.isX = true;
            check();
        }
        _imageView.setEnabled(false);
        this.counter+=1;
        if(this.counter == 9 && this.whoWon == -1) {
            Toast.makeText(this, "Game is Draw!", Toast.LENGTH_SHORT).show();
        }
        else if (this.whoWon == 1) {
            LinearLayout l23 = findViewById(R.id.linearLayout23);
            TextView t1 = findViewById(R.id.textView28), t2 = findViewById(R.id.textView2), t4 = findViewById(R.id.textView4);
            if (isPlayer1X) {
                t1.setText(this.player1Is+" Won the Game!");
                this.player1Points+=1;
                t2.setText(""+this.player1Points);
                t4.setText(""+this.player2Points);
                l23.setVisibility(View.VISIBLE);
            }
            else {
                t1.setText(this.player2Is+" Won the Game!");
                this.player2Points+=1;
                t2.setText(""+this.player1Points);
                t4.setText(""+this.player2Points);
                l23.setVisibility(View.VISIBLE);
            }
            GridLayout _grid = findViewById(R.id.gridLayout2);
            for (int i = 0; i < _grid.getChildCount(); i++) {
                View child = _grid.getChildAt(i);
                child.setEnabled(false);
            }
        }
        else if (this.whoWon == 0) {
            LinearLayout l23 = findViewById(R.id.linearLayout23);
            TextView t1 = findViewById(R.id.textView28), t2 = findViewById(R.id.textView2), t4 = findViewById(R.id.textView4);
            if (isPlayer1X) {
                t1.setText(this.player2Is+" Won the Game!");
                this.player2Points+=1;
                t2.setText(""+this.player1Points);
                t4.setText(""+this.player2Points);
                l23.setVisibility(View.VISIBLE);
            }
            else {
                t1.setText(this.player1Is+" Won the Game!");
                this.player1Points+=1;
                t2.setText(""+this.player1Points);
                t4.setText(""+this.player2Points);
                l23.setVisibility(View.VISIBLE);
            }
            GridLayout _grid = findViewById(R.id.gridLayout2);
            for (int i = 0; i < _grid.getChildCount(); i++) {
                View child = _grid.getChildAt(i);
                child.setEnabled(false);
            }
        }
    }

    public void exitCongrats(View view) {
        LinearLayout l23 = findViewById(R.id.linearLayout23);
        l23.setVisibility(View.INVISIBLE);
    }

    public void check() {
        String I0 = String.valueOf(findViewById(R.id.imageView10).getTag()), I1 = String.valueOf(findViewById(R.id.imageView11).getTag()), I2 = String.valueOf(findViewById(R.id.imageView12).getTag());
        String I3 = String.valueOf(findViewById(R.id.imageView13).getTag()), I4 = String.valueOf(findViewById(R.id.imageView14).getTag()), I5 = String.valueOf(findViewById(R.id.imageView15).getTag());
        String I6 = String.valueOf(findViewById(R.id.imageView16).getTag()), I7 = String.valueOf(findViewById(R.id.imageView17).getTag()), I8 = String.valueOf(findViewById(R.id.imageView18).getTag());
        //Toast.makeText(this, ""+I4, Toast.LENGTH_SHORT).show();

        if((I0.equals("x")&&I1.equals("x")&&I2.equals("x")) || (I3.equals("x")&&I4.equals("x")&&I5.equals("x")) || (I6.equals("x")&&I7.equals("x")&&I8.equals("x"))) {
                this.whoWon = 1;
        }
        else if((I0.equals("o")&&I1.equals("o")&&I2.equals("o")) || (I3.equals("o")&&I4.equals("o")&&I5.equals("o")) || (I6.equals("o")&&I7.equals("o")&&I8.equals("o"))) {
            this.whoWon = 0;
        }
        else if((I0.equals("x")&&I3.equals("x")&&I6.equals("x")) || (I1.equals("x")&&I4.equals("x")&&I7.equals("x")) || (I2.equals("x")&&I5.equals("x")&&I8.equals("x"))) {
                this.whoWon = 1;
        }
        else if ((I0.equals("o")&&I3.equals("o")&&I6.equals("o")) || (I1.equals("o")&&I4.equals("o")&&I7.equals("o")) || (I2.equals("o")&&I5.equals("o")&&I8.equals("o"))) {
            this.whoWon = 0;
        }
        else if((I0.equals("x")&&I4.equals("x")&&I8.equals("x")) || (I2.equals("x")&&I4.equals("x")&&I6.equals("x"))) {
                this.whoWon = 1;
        }
        else if ((I0.equals("o")&&I4.equals("o")&&I8.equals("o")) || (I2.equals("o")&&I4.equals("o")&&I6.equals("o"))) {
            this.whoWon = 0;
        }
    }

    public void restartApp (View view) {
        LinearLayout l2 = findViewById(R.id.linearLayout2);
        l2.animate().alpha(0).setDuration(2000);
        GridLayout g1 = findViewById(R.id.gridLayout);
        g1.animate().alpha(0).setDuration(2000);
        GridLayout _grid = findViewById(R.id.gridLayout2);
        _grid.animate().alpha(0).setDuration(2000);
        LinearLayout l1 = findViewById(R.id.linearLayout1);
        l1.animate().alpha(0).setDuration(2000);

        for (int i = 0; i < _grid.getChildCount(); i++) {
            View child = _grid.getChildAt(i);
            child.setEnabled(true);
            ImageView iV = findViewById(child.getId());
            iV.setImageResource(R.drawable.mask);
            iV.setTag("m");
        }

        LinearLayout l5 = findViewById(R.id.linearLayout5);
        l5.setVisibility(View.VISIBLE);
        this.isPlayer1X = true;
        this.isX = true;
        this.counter = 0;
        this.whoWon = -1;
    }

    public void hideSoftKeyboard() {
        InputMethodManager iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) {
            iMM.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setupUI(final View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void infoRules (View view) {
        ConstraintLayout c2 = findViewById(R.id.constraintLayout2);
        c2.setVisibility(View.VISIBLE);
    }

    public void exitInfo(View view) {
        ConstraintLayout c2 = findViewById(R.id.constraintLayout2);
        c2.setVisibility(View.INVISIBLE);
    }

    public void closeApp(View view) {
        finish();
        System.exit(0);
    }
}
