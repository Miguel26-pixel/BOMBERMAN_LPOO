package model.level;

import controller.ArenaController;
import gui.GUI;
import gui.LanternaGUI;
import model.arena.Arena;
import model.arena.ArenaBuilder;
import model.utils.Position;
import viewer.ArenaViewer;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NewLevel {
    private final GUI gui;
    private final ArenaController controller;
    private String filePath;

    public NewLevel(ArenaBuilder builder, GUI gui, ArenaViewer viewer) throws IOException {
        this.gui = gui;
        setFilePath();
        Arena arena = builder.buildArena(this.filePath);
        System.out.println(arena.getInitialBombers().size());
        arena.generateMonster(5);
        this.controller = new ArenaController(arena, viewer, gui);

    }

    private void setFilePath() throws IOException {
        String pathToResource = "src/main/resources/arenas";
        Random generator = new Random();
        int number = generator.nextInt(Objects.requireNonNull(new File(pathToResource).list()).length-1)+1;
        this.filePath = "arenas/arena" + number + ".txt";
    }

    public int start() throws IOException {
        controller.startSinglePlayer();
        if(controller.start() == 1) {
            gui.drawString(new Position(28,10), "GAME OVER!! :((", "#FF0000");
            gui.refresh();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }
        else {
            gui.drawString(new Position(21, 10), "CONGRATS U FINISH THE LEVEL!!", "#FF0000");
            gui.refresh();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

}
