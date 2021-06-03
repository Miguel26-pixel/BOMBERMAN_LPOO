package model;

import model.entity.*;
import model.utils.Position;
import model.utils.DirectionToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BlastTest {
    @Test
    public void updateTest(){
        Position p = Mockito.mock(Position.class);
        Blast b = new Blast(p,1,DirectionToken.DOWN);
        Assertions.assertEquals(false,b.canExpand());
        Assertions.assertEquals(false,b.canDisappear());
        for(int i=0;i<3;i++){
            b.update();
        }
        Assertions.assertEquals(true,b.canExpand());
        Assertions.assertEquals(false,b.canDisappear());
        for(int i=0;i<17;i++){
            b.update();
        }
        Assertions.assertEquals(false,b.canExpand());
        Assertions.assertEquals(true,b.canDisappear());
        b.update();
        Assertions.assertEquals(false,b.canExpand());
        Assertions.assertEquals(true,b.canDisappear());


        b = new Blast(p,1,DirectionToken.NONE);
        Assertions.assertEquals(false,b.canExpand());
        Assertions.assertEquals(false,b.canDisappear());
        for(int i=0;i<3;i++){
            b.update();
        }
        Assertions.assertEquals(true,b.canExpand());
        Assertions.assertEquals(false,b.canDisappear());
        for(int i=0;i<17;i++){
            b.update();
        }
        Assertions.assertEquals(false,b.canExpand());
        Assertions.assertEquals(true,b.canDisappear());
    }

    @Test
    public void getSet(){
        Position p = Mockito.mock(Position.class);
        Blast b = new Blast(p,1,DirectionToken.DOWN);

        Assertions.assertEquals(1,b.getBlastsLeft());

        Assertions.assertEquals(DirectionToken.DOWN,b.getBlastDirection());
        b.setBlastDirection(DirectionToken.NONE);
        Assertions.assertEquals(DirectionToken.NONE,b.getBlastDirection());

        b.setBlastsLeft(0);
        Assertions.assertEquals(0,b.getBlastsLeft());

    }
}
