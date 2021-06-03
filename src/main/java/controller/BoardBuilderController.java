package controller;

import gui.GUI;
import model.arena.Arena;
import model.arena.ArenaSaver;
import model.entity.Bomber;
import model.entity.Entity;
import model.entity.Monster;
import model.entity.Wall;
import model.utils.Position;
import viewer.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BoardBuilderController extends GameController{
    private final String title;
    private final ArenaViewer viewer;
    private final GUI gui;
    private ArenaSaver arenaSaver;
    private List<Entity> entityList = new ArrayList<>();
    private char charOnUse;
    private boolean willSave;

    public BoardBuilderController(Arena arena, ArenaViewer viewer, GUI gui, String title, ArenaSaver arenaSaver) {
        super(arena);
        this.title = title;
        this.viewer = viewer;
        this.gui = gui;
        this.arenaSaver = arenaSaver;
        this.charOnUse = '#';

    }

    public void start() throws IOException, URISyntaxException {
        gui.clear();
        showTitle();
        //this.viewer.draw(this.arena);
        showOutSideWalls();
        willSave = true;
        buildboard();
        if(willSave)
            arenaSaver.saveArena(this);
    }

    private void showTitle() throws IOException {
        int startY = 1, startX = 25;

        for(int x = startX + 1; x <= title.length() + startX; x++){
            gui.drawCharacter(new Position(x, startY), title.charAt(x-26), "#FFFFFF");
        }

        gui.refresh();
    }

    private void showOutSideWalls() throws IOException {

        int startY = 3, startX = 17;

        for(int x = startX + 1; x <= arena.getWidth() + startX; x++){
            gui.drawCharacter(new Position(x, startY), '#', "#FFFFFF");
            gui.drawCharacter(new Position(x, arena.getHeight() + 2 + startY), '#', "#FFFFFF");
        }
        for(int y=startY; y <= arena.getHeight() + startY + 2; y++){
            gui.drawCharacter(new Position(startX, y), '#', "#FFFFFF");
            gui.drawCharacter(new Position(arena.getWidth() + 1 + startX, y), '#', "#FFFFFF");
        }
        gui.refresh();
    }

    private boolean isInsideBoard(Position p) {
        return p.getX() >= 18 && p.getX() <= 47 && p.getY() >= 4 && p.getY() <= 17;
    }

    public char isEntity(Position p) {
        for (Entity pp : entityList) {
            if(pp.getPosition().getX() == p.getX() && pp.getPosition().getY() == p.getY()){
                if(pp instanceof Bomber)
                    return 'P';
                else if(pp instanceof Wall)
                    return '#';
                else
                    return 'M';
            }
        }
        return ' ';
    }

    private void pass(Position p) {
        if(isEntity(p) != ' ')
            gui.drawCharacter(p, isEntity(p), "#FFFFFF");
        else
            gui.drawCharacter(p, isEntity(p), "#000000");
    }

    public Entity getEntity(Position p) {
        for (Entity pp : entityList) {
            if(pp.getPosition().getX() == p.getX() && pp.getPosition().getY() == p.getY())
                return pp;
        }
        return null;
    }

    private Position movePosition(Position position, GUI.ACTION act) {
        Position p1 = new Position(position.getX() + 1, position.getY());
        Position p2 = new Position(position.getX() - 1, position.getY());
        Position p3 = new Position(position.getX(), position.getY() + 1);
        Position p4 = new Position(position.getX(), position.getY() - 1);

        if (isInsideBoard(new Position(position.getX() + 1, position.getY())) && act == GUI.ACTION.RIGHT) {
            pass(position);
            return p1;
        } else if (isInsideBoard(new Position(position.getX() - 1, position.getY())) && act == GUI.ACTION.LEFT) {
            pass(position);
            return p2;
        } else if (isInsideBoard(new Position(position.getX(), position.getY() + 1)) && act == GUI.ACTION.DOWN) {
            pass(position);
            return p3;
        } else if (isInsideBoard(new Position(position.getX(), position.getY() - 1)) && act == GUI.ACTION.UP) {
            pass(position);
            return p4;
        } else
            return position;
    }

    private void placeWall(Position p){
        gui.drawCharacter(new Position(p.getX(), p.getY()), '#', "#FFFFFF");
    }

    private void placePlayer(Position p){
        gui.drawCharacter(new Position(p.getX(), p.getY()), 'P', "#FFFFFF");
    }

    private void placeMonster(Position p){
        gui.drawCharacter(new Position(p.getX(), p.getY()), 'O', "#FFFFFF");
    }

    private void buildLoop(Position position, GUI.ACTION act) throws IOException {
        long startTime = System.currentTimeMillis();
        while (act != GUI.ACTION.MODES) {
            act = gui.getNextAction(true);

            if(act == GUI.ACTION.PLACEBOMB) {
                if(this.charOnUse == '#') {
                    if (position != null) {
                        if (isEntity(position) != ' ') {
                            gui.drawCharacter(position, ' ', "#000000");
                            entityList.remove(getEntity(position));
                        } else {
                            entityList.add(new Wall(position));
                            placeWall(position);
                            gui.refresh();
                        }
                    }
                }
                else
                    this.charOnUse = '#';
            }
            else if(act == GUI.ACTION.PLAYER) {
                if(this.charOnUse == 'P') {
                    if (position != null) {
                        if (isEntity(position) != ' ') {
                            gui.drawCharacter(position, ' ', "#000000");
                            entityList.remove(getEntity(position));
                        } else {
                            entityList.add(new Bomber(position));
                            placePlayer(position);
                            gui.refresh();
                        }
                    }
                }
                else
                    this.charOnUse = 'P';
            }
            else if(act == GUI.ACTION.MONSTER) {
                if(this.charOnUse == 'M') {
                    if (position != null) {
                        if (isEntity(position) != ' ') {
                            gui.drawCharacter(position, ' ', "#000000");
                            entityList.remove(getEntity(position));
                        } else {
                            entityList.add(new Monster(position));
                            placeMonster(position);
                            gui.refresh();
                        }
                    }
                }
                else
                    this.charOnUse = 'M';
            } else if(act == GUI.ACTION.QUIT){
                willSave = false;
                break;
            }

            if(position != null) {
                if ((System.currentTimeMillis() - startTime) % 4 == 0)
                    gui.drawCharacter(position, this.charOnUse, "#FF0000");
                else
                    gui.drawCharacter(position, this.charOnUse, "#FFFFFF");
            }

            gui.refresh();

            if(act != null && position != null) {
                position = movePosition(position,act);
            }

        }
    }



    private void buildboard() throws IOException {
        GUI.ACTION act = null;
        Position position = new Position(18,4);
        buildLoop(position, act);
    }
}
