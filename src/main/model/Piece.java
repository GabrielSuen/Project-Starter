package model;


public class Piece {


    public static final int DY = 1;

    private int posX;
    private int posY;
//  private int dy; // will use later when more levels are implemented

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
