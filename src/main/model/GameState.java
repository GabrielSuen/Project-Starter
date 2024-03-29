package model;

import java.util.Random;
import java.awt.event.KeyEvent;

public class GameState {

    public static final int HEIGHT = 400;
    public static final int WIDTH = 200;
    public static final Random RND = new Random();

    private boolean endedGame;
    private int score;
    private Piece piece;
    private Platform platform;

    public GameState() {
        setUp();
    }

    // sets up the game environment
    private void setUp() {
        piece = new Piece(WIDTH / 2, 0);
        platform = new Platform(RND.nextInt(WIDTH), RND.nextInt(GameState.HEIGHT - 200) + 180);
        endedGame = true;
        score = 0;
    }
    //RND.nextInt(GameState.HEIGHT / 2) + 10)

    // Updates the game on clock tick
    // modifies: this
    // effects:  updates piece and platform
    public void update() {
        piece.moveDown();
        piece.move();
        checkCollisions();
        checkGameOver();
    }

    // effects: handles user input, A = left, B = right
    public void controlPiece(int key) {
        if (key == KeyEvent.VK_RIGHT) {
            piece.moveRight();
        } else if (key == KeyEvent.VK_LEFT) {
            piece.moveLeft();
        }
    }

    // effects: checks if platform has collided with piece; adds 1 to score and resets piece to top of screen
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

    public void startGame() {
        endedGame =  false;
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
