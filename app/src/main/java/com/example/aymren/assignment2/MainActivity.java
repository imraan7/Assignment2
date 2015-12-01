package com.example.aymren.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3, nGame;
    Button [] array;
    //X = true, O = false
    Boolean turn = true;
    int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verbindt de designbuttons met de objectbuttons
        a1 = (Button) findViewById(R.id.a1);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        c3 = (Button) findViewById(R.id.c3);
        nGame = (Button) findViewById(R.id.nGame);

        array = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        playerTurn();

        nGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            turn = true;
            turnCount = 0;
            enableDisableAllButtons(true);
            }
        });
    }
    private void playerTurn(){
        for (Button b : array)
            //wanneer op de button wordt geklikt, wordt het door de clicklistener geregistreerd
            b.setOnClickListener(this);
    }
    private void computerTurn(){
        Random rnd = new Random();
        int index = rnd.nextInt(array.length);
        Button b = array[index];
        b.setText("O");
        turnCount++;
        b.setClickable(false);
        turn = true;
        checkForWinner();
        playerTurn();
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        buttonClicked(b);
    }

    private void buttonClicked(Button b) {
        if(turn)
            b.setText("X");
        turnCount++;
        b.setClickable(false);

        turn = !turn;
        checkForWinner();
        computerTurn();
    }

    private void checkForWinner() {
        Boolean winner = false;
        //Horizontaal
        if(a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable())
        winner = true;
        else if(a2.getText() == b2.getText() && b2.getText() == c2.getText() && !a2.isClickable())
        winner = true;
        else if(a3.getText() == b3.getText() && b3.getText() == c3.getText() && !a3.isClickable())
        winner = true;
        //Verticaal
        if(a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable())
            winner = true;
        else if(b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable())
            winner = true;
        else if(c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable())
            winner = true;
        //Diagonaal
        if(a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable())
            winner = true;
        else if(a3.getText() == b2.getText() && b2.getText() == c1.getText() && !a3.isClickable())
            winner = true;

        if(winner) {
            if (turn)
                toast("O Wins!");
            else
                toast("X Wins!");
            enableDisableAllButtons(false);
        }
        else if(turnCount == 9)
            toast("DRAW");
    }

    private void enableDisableAllButtons(boolean enable){
        for(Button b : array){
            b.setClickable(enable);
            if(enable) {
                b.setText("");
            }
        }
    }

    private void toast (String bericht) {
        //bericht genereren
        Toast.makeText(getApplicationContext(), bericht, Toast.LENGTH_SHORT).show();
    }

}
