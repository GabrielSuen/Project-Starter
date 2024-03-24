package model;


public class Piece {


    public static final int DY = 1;

    private int posX;
    private int posY;
    private boolean dir;
//  private int dy;  will use later when more levels are implemented

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

    public void setPosY(int y) {
        posY = y;
    }

    public void setPosX(int x) {
        posX = x;
    }

    // modifies: this
    // effects: moves the piece down by DY(1)
    public void moveDown() {
        posY = posY + DY;
    }

    // modifies: this
    // effects: moves piece left by 1 position
    public void moveLeft() {
        dir = false;
        handleBoundary();
    }

    // modifies: this
    // moves piece right by 1 position
    public void moveRight() {
        dir = true;
        handleBoundary();
    }

    // Constrains piece so that it doesn't travel off sides of screen
    // modifies: this
    // effects: piece is constrained to remain within vertical boundaries of game
    // inspo taken from https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna.git
    private void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX > GameState.WIDTH) {
            posX = GameState.WIDTH;
        }
    }

    // modifies: this
    // effects: reset position of the piece to be at the top middle of the screen
    public void resetPiece() {
        this.posX = GameState.WIDTH / 2;
        this.posY = 0;
    }


    public void move() {
        if (dir) {
            posX = posX + 1;
        } else {
            posX = posX - 1;
        }
    }
}
