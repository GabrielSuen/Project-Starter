package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlatformTest {

    private Platform platform;
    private Piece piece;

    @BeforeEach
    void runBefore() {
        platform = new Platform(GameState.WIDTH / 2, 15);
    }

    @Test
    void testMove() {
        platform.move();
        assertTrue(platform.getPosY() < GameState.HEIGHT);
        assertTrue(platform.getPosY() > GameState.HEIGHT / 2);
        assertTrue(platform.getPosX() <= GameState.WIDTH);
    }

    @Test
    void testHasCollided() {
        piece = new Piece(GameState.WIDTH / 3,15);
        assertFalse(platform.hasCollided(piece));
        piece = new Piece(GameState.WIDTH / 2,15);
        assertTrue(platform.hasCollided(piece));
    }

    @Test
    void testHasNotCollided() {
        piece = new Piece(GameState.WIDTH / 2,0);
        assertFalse(platform.hasCollided(piece));
    }

}
