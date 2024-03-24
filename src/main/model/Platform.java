package model;

import java.awt.*;
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

    // modifies: this
    // moves the platform to a random spot on the bottom half of the screen
    public void move() {
        posX = RND.nextInt(GameState.WIDTH);
        posY = RND.nextInt(GameState.HEIGHT - 200) + 180;
    }

    // Effects: returns if the platform has come in contact with Piece p
    public boolean hasCollided(Piece p) {
        Rectangle platBoundingRect = new Rectangle(getPosX(), getPosY(), 20, 5);
        Rectangle pieceBoundingRect = new Rectangle(p.getPosX(),  p.getPosY(), 10, 10);
        return platBoundingRect.intersects(pieceBoundingRect);
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

}
