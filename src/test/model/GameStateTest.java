package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
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
        assertFalse(gs.isEndedGame());
    }

    @Test
    void testUpdateNoCollisions() {
        gs.update();
        assertEquals(gs.getPiece().getPosY(), 1);
        assertEquals(gs.getScore(), 0);
        assertFalse(gs.isEndedGame());
    }

    @Test
    void testUpdateWithCollisions() {
        gs.getPlatform().setPosY(1);
        gs.getPlatform().setPosX(GameState.WIDTH / 2);
        gs.update();
        assertEquals(gs.getPiece().getPosY(), 0);
        assertEquals(gs.getScore(), 1);
        assertFalse(gs.isEndedGame());
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
        gs.controlPiece(VK_D);
        assertEquals(gs.getPiece().getPosX(), 11);
        gs.controlPiece(VK_A);
        assertEquals(gs.getPiece().getPosX(), 10);
    }



}
