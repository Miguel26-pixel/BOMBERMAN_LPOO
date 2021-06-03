package viewer;

import gui.GUI;
import model.arena.Arena;
import model.entity.Bomber;
import model.utils.Position;

import java.io.IOException;

public class ArenaViewer {
    private final GUI gui;


    public ArenaViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        gui.clear();
        int startY = 1, startX = 20;

        //Draw borders
        for(int x = startX + 1; x <= arena.getWidth() + startX; x++){
            gui.drawCharacter(new Position(x, startY), '#', "#FFFFFF");
            gui.drawCharacter(new Position(x, arena.getHeight() + 1 + startY), '#', "#FFFFFF");
        }
        for(int y=startY; y <= arena.getHeight() + startY +1; y++){
            gui.drawCharacter(new Position(startX, y), '#', "#FFFFFF");
            gui.drawCharacter(new Position(arena.getWidth() + 1 + startX, y), '#', "#FFFFFF");
        }

        //Draw board and entities
        gui.drawAll(arena.getEntities(), startX + 1, startY + 1, arena.getHeight(), arena.getWidth());

        //Draw player one info
        Bomber player1 = arena.getBomber(0);
        if(player1 != null) {
            drawBomberInfo(0, player1);
        }

        Bomber player2 = arena.getBomber(1);
        if(player2 != null){
            drawBomberInfo(1, player2);
        }
        gui.refresh();
    }

    private void drawBomberInfo(int nBomber, Bomber bomber){
        int startX = nBomber == 0 ? 1 : 53;
        gui.drawString(new Position(startX, 1), "Player " + (nBomber + 1), bomber.getColor());
        gui.drawString(new Position(startX, 3), "Health", "#FFFFFF");
        for (int i = 0; i < bomber.getHealth(); i++) {
            gui.drawCharacter(new Position(startX + i, 4), '#', "#FFFFFF");
        }
        gui.drawString(new Position(startX, 5), "Blast level", "#FFFFFF");
        for (int i = 0; i <= bomber.getBlastLevel(); i++) {
            gui.drawCharacter(new Position(startX + i, 6), '#', "#FFFFFF");
        }
        gui.drawString(new Position(startX, 7), "Bomb level", "#FFFFFF");
        for (int i = 0; i <= bomber.getBombLevel(); i++) {
            gui.drawCharacter(new Position(startX + i, 8), '#', "#FFFFFF");
        }
        gui.drawString(new Position(startX, 9), "Speed level", "#FFFFFF");
        for (int i = 0; i <= bomber.getSpeedLevel(); i++) {
            gui.drawCharacter(new Position(startX + i, 10), '#', "#FFFFFF");
        }
    }
    public void close() throws IOException {
        gui.close();
    }
}
