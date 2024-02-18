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
        assertEquals(9, p1.getPosX());
    }

    @Test
    void testMoveRight() {
        p1.moveRight();
        assertEquals(11, p1.getPosX());
    }

    @Test
    void testHandleBoundaryRight() {
        p3.moveRight();
        assertEquals(20, p3.getPosX());
    }

    @Test
    void testHandleBoundaryLeft() {
        p2.moveLeft();
        p2.moveLeft();
        assertEquals(0, p2.getPosX());
    }

    @Test
    void testResetPiece() {
        p3.resetPiece();
        assertEquals(10, p3.getPosX());
        assertEquals(0, p3.getPosY());
    }

}
