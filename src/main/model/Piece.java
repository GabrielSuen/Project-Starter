package model;

import java.awt.Color;

public class Piece {

//    public static int WIDTH = 8;
//    public static int HEIGHT = 8;
//    public static final int SIZE_X = 15;
//    public static final int SIZE_Y = 8;
    public static final int DY = 1;
//    public static final Color COLOR = new Color(250, 128, 20);

    private int posX;
    private int posY;
    private int dy; // will use later when more levels are implemented

    public Piece(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void moveDown() {
        posY = posY + DY;
    }

    public void moveLeft() {
        posX = posX - 1;
        handleBoundary();
    }

    public void moveRight() {
        posX = posX + 1;
        handleBoundary();
    }

    // Constrains piece so that it doesn't travel off sides of screen
    // modifies: this
    // effects: piece is constrained to remain within vertical boundaries of game
    private void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX > GameState.WIDTH) {
            posX = GameState.WIDTH;
        }
    }

    public void resetPiece() {
        this.posX = GameState.WIDTH / 2;
        this.posY = 0;
    }


}
