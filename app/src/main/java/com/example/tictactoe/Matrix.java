package com.example.tictactoe;
import java.io.Serializable;
import android.os.Bundle;

public class Matrix implements Serializable{

    private int[][] matrix;
    public int mSize;
    public Matrix(int size){
        mSize = size;
        matrix = new int[mSize][mSize];
    }

    public void set(int rowIndex, int colIndex, int data){

        matrix[rowIndex][colIndex] = data;
    }

    public int get(int rowIndex, int colIndex){

        return matrix[rowIndex][colIndex];
    }

    public void clear() {
    }

    public void saveState(Bundle outState) {
    }

    public void restoreState(Bundle savedInstanceState) {
    }

    //this is to reset all the matrix
//    public void resetMatrix() {
//        for (int i = 0; i <mSize ; i++) {
//            for (int j = 0; j < mSize; j++) {
//                set(i, j, 0);
//            }
//        }
//    }
}

