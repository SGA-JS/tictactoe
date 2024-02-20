package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Matrix cells;
    private int playerTurn;
    private TextView message;
    private boolean gameEnds;
    private int round;
    private boolean isComputerMode;

    private String player1symbol = "X";
    private String player2symbol = "O";


    //    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        resetGameState();
//    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Reset the game state when switching back to portrait mode
            resetGameState();
        }
//        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            // Optionally handle landscape mode configuration if needed
 //         resetGameState();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.message);
        //reset button
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGameState();
            }
        });

        // Retrieve the information passed from Home activity
        Intent intent = getIntent();
        isComputerMode = intent.getBooleanExtra("isComputer", false);

        // Other initialization code



    Configuration config = getResources().getConfiguration();
        // Initialize the matrix based on size
        //  if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            cells = new Matrix(3);
            round = 0;
            playerTurn = 1;
            gameEnds = false;
            message.setText("Player1 turn 'X'");
            //cells.resetMatrix();
            resetButtontext();
           // cells = new Matrix(cells.mSize);
        } else {
            cells = new Matrix(5);

            round = 0;
            playerTurn = 1;
            gameEnds = false;
            message.setText("Player1 turn 'X'");
            //cells.resetMatrix();
            resetButtontext();
            //cells = new Matrix(cells.mSize);

        }

      //  resetGameState();

        if (savedInstanceState != null) {
            cells = (Matrix) savedInstanceState.getSerializable("CELLS");
            playerTurn = savedInstanceState.getInt("PLAYER_TURN");
            round = savedInstanceState.getInt("ROUND");
            gameEnds = savedInstanceState.getBoolean("GAME_ENDS");
            message.setText(savedInstanceState.getString("MESSAGE"));
            restoreButtonText();
        }
//        else {
//
//            resetGameState();
//        }
    }

    private void resetGameState() {
        //message = findViewById(R.id.message);
        round = 0;
        playerTurn = 1;
        gameEnds = false;
        message.setText("Player1 turn 'X'");
        //cells.resetMatrix();
        resetButtontext();
        cells = new Matrix(cells.mSize);

    }



    private void restoreButtonText() {
        // use nested for loops to iterate over the cells and the buttons
        for (int i = 0; i < cells.mSize; i++) {
            for (int j = 0; j < cells.mSize; j++) {
                // get the value of the current cell
                int value = cells.get(i, j);
                // get the id of the current button
                int id = getResources().getIdentifier("button" + (i * cells.mSize + j + 1), "id", getPackageName());
                // find the button by id
                Button button = findViewById(id);
                // set the text of the button if the value is not 0
                if (value == 1) {
                    button.setText(player1symbol);
                } else if (value == 2) {
                    button.setText(player2symbol);

                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("CELLS", cells);
        savedInstanceState.putInt("PLAYER_TURN", playerTurn);
        savedInstanceState.putInt("ROUND", round);
        savedInstanceState.putBoolean("GAME_ENDS", gameEnds);
        savedInstanceState.putString("MESSAGE", (String) message.getText());
    }


    public void resetButtontext() {
        // Reset the text of all buttons to "-"
        Button[][] buttons = new Button[cells.mSize][cells.mSize];

// Initialize the buttons
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = findViewById(getResources().getIdentifier("button" + (i * buttons[i].length + j + 1), "id", getPackageName()));
                buttons[i][j].setText("-");
            }
        }
    }

    public void buttonClicked(View view) {
        Button button = (Button) view;
        if (button != null) {
            String buttonText = button.getText().toString();
            if (buttonText.equals("-")) {

                if (!gameEnds) {
                    executeGame(view);
                }
            }
        }
    }

    public void newGameButtonClicked(View view) {
        // Restart the game and redirect to the activity_home.xml layout
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }


    private void executeGame(View view) {
        round++;
        Button button = (Button) view;
        //button.setText(""+playerTurn);
        button.setText(playerTurn == 1 ? player1symbol : player2symbol);
        //"The winner is :"+ (winner == 1 ? player1name : player2name)
        int id = Integer.parseInt((String) view.getTag());
        int rowIndex = (id - 1) / cells.mSize;
        int colIndex = (id - 1) % cells.mSize;
        cells.set(rowIndex, colIndex, playerTurn);

        int winner = checkWinner();
        int totalround = cells.mSize * cells.mSize;

        if (winner == 1) {
            message.setText("Player1  wins");
            gameEnds = true;
        } else if (winner == 2) {
            message.setText("Player2 'O' wins");
            gameEnds = true;
        }

        if (round == totalround && winner == 0) {
            message.setText("Draw!");
            gameEnds = true;
        }

        if (!gameEnds) {
            if (playerTurn == 1) {
                playerTurn = 2;
                message.setText("Player2 turn 'O'");

                if (isComputerMode) {
                    message.setText("Computer turn 'O'");
                    computerMove();

                    winner = checkWinner();
                    if(winner ==2){
                        message.setText("Computer wins 'O'");
                        gameEnds =true;
                    }
                }

            } else {
                playerTurn = 1;
                //button.setText("O");
                message.setText("Player1 turn 'X'");
            }
        }
    }

    //computer move
    public void onComputerMoveButtonClicked(View view) {
        if (!gameEnds && isComputerMode && playerTurn == 2) {
            // If it's the computer's turn and computer mode is enabled
            computerMove();
        }
    }

    private void computerMove() {
        // Loop until we find an empty cell
        while (true) {
            int row = (int) (Math.random() * cells.mSize);
            int col = (int) (Math.random() * cells.mSize);
            if (cells.get(row, col) == 0) {
                // Make the move
                cells.set(row, col, 2);
                Button button = findViewById(getResources().getIdentifier("button" + (row * cells.mSize + col + 1), "id", getPackageName()));
                button.setText(player2symbol);
                break;
            }
        }

        // After the computer's move, switch to player 1's turn
        playerTurn = 1;
        message.setText("Player1 turn 'X'");
    }


    //0 - no winner, 1 - player 1 is the winner, 2 - player 2 is the winner
    private int checkWinner() {

        int winner = 0;
        if (checkIfWinner(1)) {
            winner = 1;
        } else if (checkIfWinner(2)) {
            winner = 2;
        }

        //check for player 2
        return winner;
    }


//    private boolean checkIfWinner(int playerNumber) {
//        boolean win = false;
//
//        // Check rows and columns
//        for (int i = 0; i < cells.mSize; i++) {
//            boolean rowWin = true;
//            boolean colWin = true;
//            for (int j = 0; j < cells.mSize; j++) {
//                rowWin &= (cells.get(i, j) == playerNumber);
//                colWin &= (cells.get(j, i) == playerNumber);
//            }
//            win |= rowWin || colWin;
//        }
//
//        // Check diagonals
//        boolean diag1Win = true;
//        boolean diag2Win = true;
//        for (int i = 0; i < cells.mSize; i++) {
//            diag1Win &= (cells.get(i, i) == playerNumber);
//            diag2Win &= (cells.get(i, cells.mSize - 1 - i) == playerNumber);
//        }
//        win |= diag1Win || diag2Win;
//
//        return win;
//    }

    private boolean checkIfWinner(int playerNumber) {
        boolean win = false;

        // Check rows
        for (int i = 0; i < cells.mSize; i++) {
            boolean rowWin = true;
            for (int j = 0; j < cells.mSize; j++) {
                rowWin &= (cells.get(i, j) == playerNumber);
            }
            if (rowWin) {
                win = true;
                break;
            }
        }

        // Check columns
        if (!win) {
            for (int j = 0; j < cells.mSize; j++) {
                boolean colWin = true;
                for (int i = 0; i < cells.mSize; i++) {
                    colWin &= (cells.get(i, j) == playerNumber);
                }
                if (colWin) {
                    win = true;
                    break;
                }
            }
        }

        // Check diagonals
        if (!win) {
            boolean diag1Win = true;
            boolean diag2Win = true;
            for (int i = 0; i < cells.mSize; i++) {
                diag1Win &= (cells.get(i, i) == playerNumber);
                diag2Win &= (cells.get(i, cells.mSize - 1 - i) == playerNumber);
            }
            if (diag1Win || diag2Win) {
                win = true;
            }
        }

        return win;
    }

}

