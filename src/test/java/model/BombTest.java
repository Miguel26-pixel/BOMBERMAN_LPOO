package model;

import model.entity.Bomb;
import model.entity.Bomber;
import model.utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BombTest {

    @Test
    public void testUpdate(){
        Bomber bomber = Mockito.mock(Bomber.class);
        Position position = Mockito.mock(Position.class);
        Bomb bomb= new Bomb(position,1,bomber);
        Assertions.assertEquals(false,bomb.canExplode());
        for(int i=0;i<20;i++){
            bomb.update();
        }
        Assertions.assertEquals(true,bomb.canExplode());
        bomb.setTicksLeft(1);
        Assertions.assertEquals(false,bomb.canExplode());
        bomb.update();
        Assertions.assertEquals(true,bomb.canExplode());
    }
}
