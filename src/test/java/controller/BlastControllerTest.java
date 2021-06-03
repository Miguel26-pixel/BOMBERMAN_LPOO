package controller;

import model.arena.Arena;
import controller.BlastController;
import controller.GameController;
import model.entity.Blast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlastControllerTest {
    private BlastController blastController;
    private Arena arena;

    @Test
    public void updateTest(){
        Blast b1 = Mockito.mock(Blast.class);
        Mockito.when(b1.canExpand()).thenReturn(true);
        Mockito.when(b1.canDisappear()).thenReturn(false);
        Blast b2 = Mockito.mock(Blast.class);
        Mockito.when(b2.canExpand()).thenReturn(false);
        Mockito.when(b2.canDisappear()).thenReturn(false);
        arena = Mockito.mock(Arena.class);
        List<Blast> blasts = new ArrayList<>();
        blasts.add(b1);blasts.add(b2);
        Mockito.when(arena.getBlasts()).thenReturn(blasts);
        blastController = new BlastController(arena);
        //blastController.updateBlasts();
        //Mockito.verify(arena,Mockito.times(1)).removeBlast(Mockito.any());
        //Mockito.verify(blastController,Mockito.times(1)).expandBlast(Mockito.any());
    }
}
