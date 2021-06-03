package controller;

import controller.ArenaController;
import gui.GUI;
import gui.Key;
import model.arena.Arena;
import model.entity.*;
import model.utils.Position;
import model.entity.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.ArenaViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaControllerTest {
    private Arena arena;
    private MonsterController monsterController;
    private PlayerController playerController;
    private BombController bombController;
    private BlastController blastController;
    private WallController wallController;
    private CollisionController collisionController;
    private EffectController effectController;
    private ArenaViewer viewer;
    private GUI gui;
    private ArenaController arenacontroller;


    @BeforeEach
    void setUp() throws IOException {
        arena = Mockito.mock(Arena.class);
        viewer = Mockito.mock(ArenaViewer.class);
        gui= Mockito.mock(GUI.class);
        effectController = Mockito.mock(EffectController.class);
        playerController = Mockito.mock(PlayerController.class);
        monsterController = Mockito.mock(MonsterController.class);
        blastController = Mockito.mock(BlastController.class);
        bombController = Mockito.mock(BombController.class);
        wallController = Mockito.mock(WallController.class);
        collisionController = Mockito.mock(CollisionController.class);

        arenacontroller = new ArenaController(arena,viewer,gui);
    }

    @Test
    public void startTest() {
        try {
            Mockito.when(gui.getKey()).thenReturn(Key.A).thenReturn(Key.Q);
            arenacontroller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Mockito.verify(playerController,Mockito.times(0)).handleInput(Key.A);
        Mockito.verify(monsterController,Mockito.times(0)).moveMonsters();
        Mockito.verify(bombController,Mockito.times(0)).updateBombs();
        Mockito.verify(blastController,Mockito.times(0)).updateBlasts();
        Mockito.verify(collisionController,Mockito.times(0)).handleCollisions();
    }

    @Test
    public void gameOver(){
        Monster monster = Mockito.mock(Monster.class);
        Bomber bomber1 = Mockito.mock(Bomber.class);
        Mockito.when(bomber1.getHealth()).thenReturn(0);
        Bomber bomber2 = Mockito.mock(Bomber.class);
        Mockito.when(bomber1.getHealth()).thenReturn(1);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        List<Bomber> bombers = new ArrayList<>();
        bombers.add(bomber1);
        bombers.add(bomber2);
        Mockito.when(arena.getMonsters()).thenReturn(monsters).thenReturn(new ArrayList<>()).thenReturn(monsters);
        Mockito.when(arena.getBombers()).thenReturn(bombers);

        Assertions.assertEquals(1,arenacontroller.checkGameover());
        Assertions.assertEquals(0,arenacontroller.checkGameover());
        Assertions.assertEquals(1,arenacontroller.checkGameover());
    }

}
