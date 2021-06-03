package controller;

import gui.GUI;
import gui.LanternaGUI;
import model.arena.Arena;
import model.arena.ArenaBuilder;
import viewer.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BoardSelector {
    private final int nPlayers;
    private final GUI gui;
    private final ArenaViewer arenaViewer;
    private Arena arena;
    private int arenaIndex;
    private List<Arena> arenas;


    public BoardSelector(int nPlayers, GUI gui) {
        this.nPlayers = nPlayers;
        this.gui = gui;
        arenaViewer = new ArenaViewer(gui);
        loadArenas();
        arena = arenas.get(0);
    }

    private void loadArenas(){
        this.arenas = new ArrayList<>();
        ArenaBuilder arenaBuilder = new ArenaBuilder();
        int nArena = 1;
        do {
            Arena arena = arenaBuilder.buildArena("arenas/arena" + nArena++ + ".txt");
            if (arena == null) {
                break;
            }
            arenas.add(arena);
        } while(true);
    }

    public Arena getRandomArena(){
        return arenas.get(new Random().nextInt(arenas.size()));
    }

    private void drawArena(){
        gui.clear();
        try {
            arenaViewer.draw(arena);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Arena selectArena(){
        arena = null;
        boolean isSelected = false;
        do{
            arena = arenas.get(arenaIndex);
            drawArena();
            GUI.ACTION action = null;
            try {
                action = gui.getNextAction(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(action == GUI.ACTION.LEFT){
                previousBoard();
            } else if(action == GUI.ACTION.RIGHT){
                nextBoard();
            } else if(action == GUI.ACTION.MODES){
                isSelected = true;
            }


        }while(!isSelected);
        return arena;
    }

    private void nextBoard(){
        arenaIndex++;
        if(arenaIndex == arenas.size()) arenaIndex = 0;
    }

    private void previousBoard(){
        arenaIndex--;
        if(arenaIndex == -1) arenaIndex = arenas.size() - 1;
    }


}
