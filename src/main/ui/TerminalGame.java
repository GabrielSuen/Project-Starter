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

    // starts the game
    public void start() throws IOException, InterruptedException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        gameState = new GameState();

        beginTicks();
    }

    // inspo taken from https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna.git
    private void beginTicks() throws IOException, InterruptedException {
        while (!gameState.isEndedGame()) {
            tick();
            Thread.sleep(1000L / GameState.TICKS_PER_SECOND);
        }

    }

    // progresses the game
    // inspo taken from https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna.git
    private void tick() throws IOException {
        handleUserInput();

        gameState.update();

        screen.clear();
        render();
        screen.refresh();

    }

    // effects: draws the piece and platform onto screen
    private void render() {
        drawPiece(gameState.getPiece());
        drawPlatform(gameState.getPlatform());
    }

    // effects: draws the piece onto screen
    private void drawPiece(Piece p) {
        TextColor c = TextColor.ANSI.RED;
        int px = p.getPosX();
        int py = p.getPosY();
        drawCharacter('o', c, px, py);
    }

    // effects: draws the platform onto screen
    private void drawPlatform(Platform pl) {
        TextColor c = TextColor.ANSI.GREEN;
        int px = pl.getPosX();
        int py = pl.getPosY();
        drawCharacter('_', c, px, py);
    }

    // draws any given character onto screen at given x and y position
    // inspo taken from https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna.git
    private void drawCharacter(char c, TextColor color, int x, int y) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);
        text.putString(x, y + 1, String.valueOf(c));
    }

    // Effects: checks for user input, controls piece accordingly
    // inspo taken from https://github.students.cs.ubc.ca/CPSC210/SnakeConsole-Lanterna.git
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
