package gui;

import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import model.entity.Entity;
import model.utils.Position;

public class LanternaGUI implements GUI{
    private TerminalScreen screen;
    private int width;
    private int height;
    public LanternaGUI(int width, int height) {
        this.width = width;
        this.height = height;
        AWTTerminalFontConfiguration fontConfig = null;
        try {
            fontConfig = loadSquareFont();
            Terminal terminal = createTerminal(width, height, fontConfig);
            screen = createScreen(terminal);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void drawAll(List<Entity> entities, int offsetX, int offsetY, int arenaHeight, int arenaWidth) {

        for(Entity entity: entities){
            Position position = entity.getPosition();
            if(position.isOutOfBounds(arenaWidth, arenaHeight))
                continue;
            drawEntity(entity, offsetX, offsetY);
        }
    }

    @Override
    public void drawEntity(Entity entity, int offsetX, int offsetY) {
        Position position = new Position(entity.getPosition().getX() + offsetX, entity.getPosition().getY() + offsetY);
        drawCharacter(position, entity.getCharacter(), entity.getColor());
    }

    @Override
    public void drawCharacter(Position position, char character, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX() + 1, position.getY() + 1, "" + character);
    }

    @Override
    public void drawString(Position position, String str, String color){
        for(char c : str.toCharArray()){
            drawCharacter(position, c, color);
            position = position.getRight();
        }
    }

    private TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("custom4.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 20);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public Key getKey() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return null;

        if (keyStroke.getKeyType() == KeyType.EOF) return Key.Q;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return Key.Q;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return Key.ARROW_UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return Key.ARROW_RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return Key.ARROW_DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return Key.ARROW_LEFT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') return Key.SPACEBAR;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'w') return Key.W;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'a') return Key.A;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 's') return Key.S;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'd') return Key.D;
        if (keyStroke.getKeyType() == KeyType.Enter ) return Key.ENTER;

        return null;

        //if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.MODES;

    }
    public ACTION getNextAction(boolean isPoll) throws IOException {
        KeyStroke keyStroke;
        if(isPoll){
            keyStroke = screen.pollInput();
        } else {
            keyStroke = screen.readInput();
        }
        //KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        if (keyStroke.getKeyType() == KeyType.EOF) return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') return ACTION.PLACEBOMB;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'p') return ACTION.PLAYER;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'm') return ACTION.MONSTER;
        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.MODES;
        return ACTION.NONE;
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public int getHeight(){
        return height;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
