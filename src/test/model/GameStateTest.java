package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameStateTest {

    GameState gs;

    @BeforeEach
    void runBefore() {
        gs = new GameState();
    }

    @Test
    void testSetUp() {
        assertEquals(gs.getPiece().getPosY(), 0);
        assertEquals(gs.getPiece().getPosX(), GameState.WIDTH / 2);
        assertEquals(gs.getScore(), 0);
        assertTrue(gs.isEndedGame());
    }

    @Test
    void testUpdateNoCollisions() {
        gs.update();
        assertEquals(gs.getPiece().getPosY(), 1);
        assertEquals(gs.getScore(), 0);
        assertTrue(gs.isEndedGame());
    }

    @Test
    void testUpdateWithCollisions() {
        gs.getPlatform().setPosY(1);
        gs.getPlatform().setPosX(GameState.WIDTH / 2);
        gs.update();
        assertEquals(gs.getPiece().getPosY(), 0);
        assertEquals(gs.getScore(), 1);
        assertTrue(gs.isEndedGame());
    }

    @Test
    void testUpdateGameOver() {
        gs.getPiece().setPosY(19);
        gs.getPiece().setPosX(19);
        gs.getPlatform().setPosX(GameState.WIDTH / 2);
        gs.update();
        assertTrue(gs.isEndedGame());
    }

    @Test
    void testControlPiece() {
        gs.controlPiece(VK_RIGHT);
        assertEquals(gs.getPiece().getDirection(), true);
        gs.controlPiece(VK_LEFT);
        assertEquals(gs.getPiece().getDirection(), false);
    }

    @Test
    void testControlPieceLeft() {
        gs.controlPiece(VK_LEFT);
        assertEquals(gs.getPiece().getDirection(), false);
        assertEquals(gs.getPiece().getPosY(), 0);
    }

    @Test
    void testControlPieceNonInput() {
        gs.controlPiece(VK_L);
        assertEquals(gs.getPiece().getDirection(), false);
        assertEquals(gs.getPiece().getPosY(), 0);
    }

    @Test
    void isGameOverTest() {
        gs.startGame();
        assertEquals(false, gs.isEndedGame());
    }

    @Test
    void lostGameTest() {
        gs.startGame();
        gs.getPiece().setPosY(399);
        gs.update();
        assertEquals(true, gs.isEndedGame());

    }

}
