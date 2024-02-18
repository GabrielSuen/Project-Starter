package ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.GameState;
import model.Piece;
import model.Platform;

import java.io.IOException;

public class TerminalGame {

    private GameState gameState;
    private Screen screen;

    
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        gameState = new GameState();

        beginTicks();
    }

    private void beginTicks() throws IOException, InterruptedException {
        while (!gameState.isEndedGame()) {
            tick();
            Thread.sleep(1000L / GameState.TICKS_PER_SECOND);
        }

        //System.exit(0);
    }

    private void tick() throws IOException {
        handleUserInput();

        gameState.update();

     //   screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();
        render();
        screen.refresh();

     //   screen.setCursorPosition(new TerminalPosition(screen.getTerminalSize().getColumns() - 1, 0));
    }

    private void render() {
        drawPiece(gameState.getPiece());
        drawPlatform(gameState.getPlatform());
    }

    private void drawPiece(Piece p) {
        TextColor c = TextColor.ANSI.RED;
        int px = p.getPosX();
        int py = p.getPosY();
        drawCharacter('o', c, px, py);
    }

    private void drawPlatform(Platform pl) {
        TextColor c = TextColor.ANSI.GREEN;
        int px = pl.getPosX();
        int py = pl.getPosY();
        drawCharacter('_', c, px, py);
    }

    private void drawCharacter(char c, TextColor color, int x, int y) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(x, y + 1, String.valueOf(c));
    }

    private void handleUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();


        if (stroke == null || stroke.getCharacter() == null) {
            return;
        }

        char c = stroke.getCharacter();
        gameState.controlPiece(c);
    }

    public int getScore() {
        return gameState.getScore();
    }



}
