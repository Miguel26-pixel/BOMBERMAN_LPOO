package controller;

import model.arena.Arena;
import controller.BombController;
import model.entity.Bomb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class BombControllerTest {
    private BombController bombController;
    private Arena arena;

     @BeforeEach
    void setUp(){
         Bomb b1 = Mockito.mock(Bomb.class);
         Mockito.when(b1.canExplode()).thenReturn(true);
         Bomb b2 = Mockito.mock(Bomb.class);
         Mockito.when(b2.canExplode()).thenReturn(false);
         arena = Mockito.mock(Arena.class);
         Mockito.when(arena.getBombs()).thenReturn(Arrays.asList(new Bomb[]{b1, b2}));
         BlastController bc = Mockito.mock(BlastController.class);
         //Mockito.when(bc.expandBlast(Mockito.any())).
         bombController = new BombController(arena,bc);
     }

     @Test
     public void updateExplodeTest(){
         //bombController.updateBombs();
         //Mockito.verify(arena,Mockito.times(1)).removeEntity(Mockito.any());
     }
}
