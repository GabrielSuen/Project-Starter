package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private Piece p1;
    private Piece p2;
    private Piece p3;

    @BeforeEach
    void runBefore() {
        p1 = new Piece(GameState.WIDTH / 2, 0);
        p2 = new Piece(1, GameState.HEIGHT);
        p3 = new Piece(GameState.WIDTH, 10);
    }

    @Test
    void testMoveDown() {
        p1.moveDown();
        assertEquals(1, p1.getPosY());
    }

    @Test
    void testMoveLeft() {
        p1.moveLeft();
        p1.move();
        assertEquals(99, p1.getPosX());
    }

    @Test
    void testMoveRight() {
        p1.moveRight();
        p1.move();
        assertEquals(101, p1.getPosX());
    }

    @Test
    void testHandleBoundaryRight() {
        p3.moveRight();
        p3.move();
        assertEquals(190, p3.getPosX());
    }

    @Test
    void testHandleBoundaryLeft() {
        p2.moveLeft();
        p2.move();
        p2.move();
        assertEquals(0, p2.getPosX());
    }

    @Test
    void testResetPiece() {
        p3.resetPiece();
        assertEquals(100, p3.getPosX());
        assertEquals(0, p3.getPosY());
    }

}
