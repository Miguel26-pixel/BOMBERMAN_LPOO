package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import gui.GUI;
import model.arena.Arena;
import model.utils.Position;
import viewer.ArenaViewer;

import java.io.IOException;

public class MenuController{
    private final String title;
    private final GUI gui;
    private final ArenaViewer viewer;
    private int  modeSelected;

    public MenuController(String title,GUI gui, ArenaViewer viewer) {

        this.title = title;
        this.gui = gui;
        this.viewer = viewer;
    }

    public int getModeSelected(){
        return this.modeSelected;
    }

    public void setModeSelected(int mode){
        this.modeSelected = mode;
    }

    public void showMenu() throws IOException {
        gui.clear();

        //showMenuBoard();

        showTitle();

        showModes();
    }

    private void showTitle() throws IOException {
        int startY = 4, startX = 25;

        for(int x = startX + 1; x <= title.length() + startX; x++){
            gui.drawCharacter(new Position(x, startY), title.charAt(x-26), "#FFFFFF");
        }

        gui.refresh();
    }

    /*
    private void showMenuBoard() throws IOException {
        viewer.draw(getArena());
    }

     */

    private void showModes() throws IOException {
        GUI.ACTION act = null;
        while(true) {
            GUI.ACTION action = gui.getNextAction(true);
            if(action == GUI.ACTION.MODES){
                break;
            }
        }
        int startSingle = 0;
        int startMulti = gui.getWidth() - 30;
        int startBoardBuider = gui.getHeight() - 20;
        for(int i = 0; i < 5; i++) {
            startSingle += 2;
            startMulti -= 2;
            startBoardBuider -= 2;
            gui.clear();
            //showMenuBoard();
            showTitle();
            showSingle(startSingle, gui.getHeight() / 4 , 0);
            showMulti(startMulti, gui.getHeight() / 4 ,0);
            showBoardBuilder(26, startBoardBuider, 0);
        }
        while(act != GUI.ACTION.LEFT && act != GUI.ACTION.RIGHT){
             act = gui.getNextAction(true);
             if(act == GUI.ACTION.QUIT)
                 viewer.close();
        }
        int stateBefore = 1;
        while(true) {
            if(act == GUI.ACTION.LEFT && stateBefore == 1){
                stateBefore = 0;
                act = animationSingle();
                if(act == GUI.ACTION.MODES){
                    setModeSelected(0);
                    break;
                }

            }
            else if(act == GUI.ACTION.LEFT && stateBefore == 2){
                stateBefore = 1;
                act = animationBoardBuilder();
                if(act == GUI.ACTION.MODES){
                    setModeSelected(1);
                    break;
                }

            }
            else if(act == GUI.ACTION.RIGHT && stateBefore == 1){
                stateBefore = 2;
                act = animationMulti();
                if(act == GUI.ACTION.MODES){
                    setModeSelected(2);
                    break;
                }

            }
            else if(act == GUI.ACTION.RIGHT && stateBefore == 0){
                stateBefore = 1;
                act = animationBoardBuilder();
                if(act == GUI.ACTION.MODES){
                    setModeSelected(1);
                    break;
                }

            }

        }


    }

    private void showSingle(int startX, int startY, int animation) throws IOException {
        String name = " SINGLEPLAYER ";
        String line = "--------------";

        if(animation == 0) {
            for (int x = 0; x < name.length(); x++, startX++) {
                gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");
            }
        }
        else if(animation == 1){
            for (int x = 0; x < name.length(); x++, startX++) {

                if(x % 2 == 0) {

                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FF0000");

                }
                else{

                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");

                }
            }
        }
        else {
            for (int x = 0; x < name.length(); x++, startX++) {
                if(x % 2 != 0) {
                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FF0000");
                }
                else{
                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");
                }
            }
        }


        gui.refresh();

    }

    private void showMulti(int startX, int startY, int animation) throws IOException {
        String name = " MULTIPLAYER ";
        String line = "--------------";

        if(animation == 0) {
            for (int x = 0; x < name.length(); x++, startX++) {
                gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");
            }
        }
        else if(animation == 1){
            for (int x = 0; x < name.length(); x++, startX++) {

                if(x % 2 == 0) {

                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FF0000");

                }
                else{

                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");

                }
            }
        }
        else {
            for (int x = 0; x < name.length(); x++, startX++) {
                if(x % 2 != 0) {
                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FF0000");
                }
                else{
                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");
                }
            }
        }

        gui.refresh();

    }

    private void showBoardBuilder(int startX, int startY, int animation) throws IOException {
        String name = " BOARDBUILDER ";
        String line = "--------------";
        startX--;

        if(animation == 0) {
            for (int x = 0; x < name.length(); x++, startX++) {
                gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");
            }
        }
        else if(animation == 1){
            for (int x = 0; x < name.length(); x++, startX++) {

                if(x % 2 == 0) {

                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FF0000");

                }
                else{

                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");

                }
            }
        }
        else {
            for (int x = 0; x < name.length(); x++, startX++) {
                if(x % 2 != 0) {
                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FF0000");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FF0000");
                }
                else{
                    gui.drawCharacter(new Position(startX, startY), line.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 2), name.charAt(x), "#FFFFFF");
                    gui.drawCharacter(new Position(startX, startY + 4), line.charAt(x), "#FFFFFF");
                }
            }
        }


        gui.refresh();

    }

    private GUI.ACTION animationSingle() throws IOException {
        long startTime = System.currentTimeMillis();
        while(true) {
            if((System.currentTimeMillis()-startTime) % 2 == 0){
                showBoardBuilder(26, gui.getHeight() - 30, 0);
                showSingle(10, gui.getHeight() / 4 ,1);
                showMulti(gui.getWidth() - 40, gui.getHeight() / 4 ,0);
            }
            else{
                showBoardBuilder(26, gui.getHeight() - 30, 0);
                showSingle(10, gui.getHeight() / 4 ,2);
                showMulti(gui.getWidth() - 40, gui.getHeight() / 4 ,0);
            }
            GUI.ACTION action = gui.getNextAction(true);
            if(action == GUI.ACTION.RIGHT)
                return GUI.ACTION.RIGHT;
            if(action == GUI.ACTION.MODES)
                return GUI.ACTION.MODES;
            if(action == GUI.ACTION.QUIT)
                viewer.close();
        }
    }

    private GUI.ACTION animationBoardBuilder() throws IOException {
        long startTime = System.currentTimeMillis();
        while(true) {
            if((System.currentTimeMillis()-startTime) % 2 == 0){
                showBoardBuilder(26, gui.getHeight() - 30, 1);
            }
            else{
                showBoardBuilder(26, gui.getHeight() - 30, 2);
            }
            showSingle(10, gui.getHeight() / 4 ,0);
            showMulti(gui.getWidth() - 40, gui.getHeight() / 4 ,0);

            GUI.ACTION action = gui.getNextAction(true);
            if(action == GUI.ACTION.LEFT)
                return GUI.ACTION.LEFT;
            if(action == GUI.ACTION.RIGHT)
                return GUI.ACTION.RIGHT;
            if(action == GUI.ACTION.MODES)
                return GUI.ACTION.MODES;
            if(action == GUI.ACTION.QUIT)
                viewer.close();
        }
    }

    private GUI.ACTION animationMulti() throws IOException {
        long startTime = System.currentTimeMillis();
        while(true) {
            if((System.currentTimeMillis()-startTime) % 2 == 0){
                showBoardBuilder(26, gui.getHeight() - 30, 0);
                showSingle(10, gui.getHeight() / 4 ,0);
                showMulti(gui.getWidth() - 40, gui.getHeight() / 4 ,1);
            }
            else{
                showBoardBuilder(26, gui.getHeight() - 30, 0);
                showSingle(10, gui.getHeight() / 4 ,0);
                showMulti(gui.getWidth() - 40, gui.getHeight() / 4 ,2);
            }
            GUI.ACTION action = gui.getNextAction(true);
            if(action == GUI.ACTION.MODES)
                return GUI.ACTION.MODES;
            if(action == GUI.ACTION.LEFT)
                return GUI.ACTION.LEFT;
            if(action == GUI.ACTION.QUIT)
                viewer.close();
        }
    }

}
