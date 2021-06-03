package controller;

import controller.collision.*;
import gui.GUI;
import gui.Key;
import model.entity.*;
import model.arena.*;
import model.player.Player;
import viewer.ArenaViewer;

import java.io.IOException;
import java.util.List;

public class ArenaController extends GameController{
    //private final HeroController heroController;
    private final MonsterController monsterController;
    private final BomberController bomberController;
    private final BombController bombController;
    private final BlastController blastController;
    private final WallController wallController;
    private final CollisionController collisionController;
    private final EffectController effectController;
    private final PlayerController playerController;
    private final ArenaViewer viewer;
    private final GUI gui;

    public ArenaController(Arena arena, ArenaViewer viewer, GUI gui) {
        super(arena);
        effectController = new EffectController(arena);
        this.bomberController = new BomberController(arena, effectController);
        this.monsterController = new MonsterController(arena);
        this.blastController = new BlastController(arena);
        this.bombController = new BombController(arena, blastController);
        this.wallController = new WallController(arena);
        this.collisionController = new CollisionController(arena);
        this.playerController = new PlayerController(arena, bomberController);
        this.viewer = viewer;

        addCollisionHandlers();

        this.gui = gui;

    }


    public int checkGameover() {
        if (getArena().getMonsters().size() == 0) {
            return 0;
        }
        for (Bomber bomber : getArena().getBombers()) {
            if (bomber.getHealth() == 0) {
                return 1;
            }
        }
        return 2;
    }

    public int start() throws IOException {
        int FPS = 20;
        int frameTime = 1000/FPS;
        boolean game = true;
        int whyGameOver = 0;
        while (game) {
            long startTime = System.currentTimeMillis();
            updateEntities();
            Key key = gui.getKey();
            if(key == Key.Q) {
                whyGameOver = 1;
                break;
            }
            playerController.handleInput(key);
            monsterController.moveMonsters();
            bombController.updateBombs();
            blastController.updateBlasts();
            collisionController.handleCollisions();

            whyGameOver = checkEndGame();
            game = whyGameOver == 2;
            //destroyEntities(blastController.getDestroyedEntities());


            viewer.draw(getArena());
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            if(sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e){

            }

        }
        //viewer.close();
        return whyGameOver;
    }

    public void startSinglePlayer() throws IOException {
        addPlayer(1);
        //arena.addMonster();
        this.start();
    }

    public void startMultiPlayer() throws IOException {
        addPlayer(1);
        addPlayer(2);
        this.start();
    }

    public void addCollisionHandlers(){
        collisionController.addCollisionHandler(Blast.class, new BlastCollisionHandler(blastController));
        collisionController.addCollisionHandler(Bomb.class, new BombCollisionHandler(bombController));
        collisionController.addCollisionHandler(Bomber.class, new BomberCollisionHandler(bomberController));
        collisionController.addCollisionHandler(Monster.class, new MonsterCollisionHandler(monsterController));
        collisionController.addCollisionHandler(Wall.class, new WallCollisionHandler(wallController));
        //collisionController.addCollisionHandler(Upgrade.class, new UpgradeCollisionHandler());
    }

    public int checkEndGame(){
        if(playerController.getNumberPlayers() == 1){

            if (getArena().getMonsters().size() == 0) {
                return 0;
            }
            for (Bomber bomber : getArena().getBombers()) {
                if (bomber.getHealth() == 0) {
                    return 1;
                }
            }
            return 2;

        } else {
            Player winner = playerController.getWinner();
            if(winner == null){
                return 2;
            }
            return 1;
        }
    }

    public void updateEntities(){
        for(Entity entity : getArena().getEntities()){
            entity.update();
        }
    }

    public void addPlayer(int n){
        playerController.addPlayer(n);
    }
}
