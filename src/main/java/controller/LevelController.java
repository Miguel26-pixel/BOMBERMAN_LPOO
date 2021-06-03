package controller;

import gui.GUI;
import model.arena.ArenaBuilder;
import model.level.NewLevel;
import viewer.ArenaViewer;

import java.io.IOException;

public class LevelController {
    private final ArenaBuilder builder;
    private final GUI gui;
    private final ArenaViewer viewer;
    public LevelController(ArenaBuilder builder, GUI gui, ArenaViewer viewer){
        this.builder = builder;
        this.gui = gui;
        this.viewer = viewer;
    }
    public void start() throws IOException {
        int result;
        do{
            NewLevel newLevel = new NewLevel(this.builder, this.gui, this.viewer);
            result = newLevel.start();
        }while(result == 0);
    }
}
