import controller.ArenaController;
import controller.BoardBuilderController;
import controller.LevelController;
import controller.BoardSelector;
import controller.MenuController;
import gui.GUI;
import gui.LanternaGUI;
import model.arena.Arena;
import model.arena.ArenaBuilder;
import model.arena.ArenaSaver;
import viewer.ArenaViewer;

import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    public static void main(String[] args) {

        int width = 80, height = 40;

        GUI gui = new LanternaGUI(width, height);
        ArenaViewer viewer = new ArenaViewer(gui);
        ArenaSaver arenaSaver = new ArenaSaver();
		MenuController menu = new MenuController("BOMBERMAN GAME", gui, viewer);
        try {
            while(gui.getNextAction(true) != GUI.ACTION.QUIT) {
                menu.showMenu();
                if (menu.getModeSelected() == 0) {

                    LevelController levelController = new LevelController(new ArenaBuilder(), gui, viewer);
                    levelController.start();
                } else if (menu.getModeSelected() == 1) {
                    BoardBuilderController bbcontroller = new BoardBuilderController(new Arena(30, 15), viewer, gui, "BOARDBUILDER", arenaSaver);
                    bbcontroller.start();
                } else if (menu.getModeSelected() == 2) {
                    BoardSelector boardSelector = new BoardSelector(2, gui);
                    Arena arena = boardSelector.selectArena();
                    ArenaController controller = new ArenaController(arena, viewer, gui);
                    controller.startMultiPlayer();
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
