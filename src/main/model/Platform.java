package model;

import java.util.Random;

public class Platform {

    public static final Random RND = new Random();

    private int posX;
    private int posY;

    // Constructs a platform with x and y coordinates
    public Platform(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void move() {
        posX = RND.nextInt(GameState.WIDTH);
        posY = RND.nextInt(GameState.HEIGHT / 2) + 12;
    }

    // Effects: returns if the platform has come in contact with Piece p
    public boolean hasCollided(Piece p) {
        return p.getPosX() == this.getPosX() && p.getPosY() == this.getPosY();
    }

}
