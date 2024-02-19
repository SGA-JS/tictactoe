package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startNewGame(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        // Switch to the AnotherActivity
        startActivity(myIntent);

        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show();
        Log.i("appinfo", "The user clicked the button");
    }
}