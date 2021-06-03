package controller;

import controller.BomberController;
import gui.GUI;
import model.arena.Arena;
import model.utils.DirectionToken;
import model.utils.Position;
import model.entity.Bomber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BomberControllerTest {
    private BomberController bomberController;
    private Arena arena;
    private EffectController effectController;

    @BeforeEach
    void setUp(){
        arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getHeight()).thenReturn(10);
        Mockito.when(arena.getWidth()).thenReturn(10);
        effectController = Mockito.mock(EffectController.class);
        Mockito.when(effectController.isInvulnerable(Mockito.any())).thenReturn(false);
        bomberController = new BomberController(arena,effectController);
    }

    @Test
    public void moveTest(){
        Bomber bomber = Mockito.mock(Bomber.class);
        Mockito.when(bomber.setDirection(Mockito.any(DirectionToken.class))).thenReturn(false);
        Position p1 = new Position(0,0);
        Mockito.when(bomber.getPosition()).thenReturn(p1);
        Mockito.when(arena.getBomber(Mockito.any(int.class))).thenReturn(bomber);
        Mockito.when(bomber.canMove()).thenReturn(true);
        bomberController.doAction(bomber,GUI.ACTION.RIGHT);
        bomberController.doAction(bomber,GUI.ACTION.DOWN);
        bomberController.doAction(bomber,GUI.ACTION.UP);
        bomberController.doAction(bomber,GUI.ACTION.LEFT);
        Mockito.verify(bomber,Mockito.times(2)).setPosition(Mockito.any(Position.class));
    }

    @Test
    public void decreaseTest(){
        Bomber bomber = Mockito.mock(Bomber.class);
        Mockito.when(bomber.getHealth()).thenReturn(0).thenReturn(1);
        bomberController.decreaseHealth(bomber);
        bomberController.decreaseHealth(bomber);
        Mockito.verify(arena,Mockito.times(1)).removeEntity(bomber);
        Mockito.verify(bomber,Mockito.times(1)).setGotHit(true);
    }
}
