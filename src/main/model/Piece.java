package model;

// represents the falling object you control
public class Piece {

    public static final int DY = 1;
    private int posX;
    private int posY;
    private boolean dir;

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

    public boolean getDirection() {
        return dir;
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
    // effects: changes direction of piece to left
    public void moveLeft() {
        dir = false;
    }

    // modifies: this
    // effects: changes direction of piece to right
    public void moveRight() {
        dir = true;
    }

    // Constrains piece so that it doesn't travel off sides of screen
    // modifies: this
    // effects: piece is constrained to remain within vertical boundaries of game
    // inspo taken from https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna.git
    private void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX > GameState.WIDTH - 10) {
            posX = GameState.WIDTH - 10;
        }
    }

    // modifies: this
    // effects: reset position of the piece to be at the top middle of the screen
    public void resetPiece() {
        this.posX = GameState.WIDTH / 2;
        this.posY = 0;
    }

    // moves the piece left or right
    public void move() {
        if (dir) {
            posX = posX + 1;
        } else {
            posX = posX - 1;
        }
        handleBoundary();
    }
}
