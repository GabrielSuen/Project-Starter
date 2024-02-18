package model;

import java.util.Random;
import java.awt.event.KeyEvent;

public class GameState {

    public static final int HEIGHT = 20;
    //public static int HEIGHT = 20;
    public static final int WIDTH = 20;
    public static final Random RND = new Random();
    public static final int TICKS_PER_SECOND = 10;

    private boolean endedGame;
    private int score;
    private Piece piece;
    private Platform platform;

    public GameState(int width, int height) {
        width = WIDTH;
        height = HEIGHT;
        setUp();
    }

    // sets up the game environment
    private void setUp() {
        piece = new Piece(WIDTH / 2, 0);
        platform = new Platform(RND.nextInt(WIDTH), RND.nextInt(GameState.HEIGHT / 2) + 10);
        endedGame = false;
        score = 0;
    }

    // Updates the game on clock tick
    // modifies: this
    // effects:  updates piece and platform
    public void update() {
        piece.moveDown();
        checkCollisions();
        checkGameOver();
    }

    public void controlPiece(int key) {
        if (key == KeyEvent.VK_D) {
            piece.moveRight();
        } else if (key == KeyEvent.VK_A) {
            piece.moveLeft();
        }
    }

    private void checkCollisions() {
        if (platform.hasCollided(piece)) {
            platform.move();
            piece.resetPiece();
            score++;
        }
    }

    // checks if the game has ended
    private void checkGameOver() {
        if (piece.getPosY() >= HEIGHT) {
            endedGame = true;
        }
    }

    public boolean isEndedGame() {
        return endedGame;
    }

    public Piece getPiece() {
        return piece;
    }

    public Platform getPlatform() {
        return platform;
    }

    public int getScore() {
        return score;
    }


}
