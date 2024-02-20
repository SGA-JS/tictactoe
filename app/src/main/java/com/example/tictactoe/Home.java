package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private boolean isComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btn_playermode(View view) {
        isComputer = false;
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("isComputer", isComputer);
        startActivity(myIntent);
    }

    public void onComputerMoveButtonClicked(View view) {
        isComputer = true;
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("isComputer", isComputer);
        startActivity(myIntent);
    }

}