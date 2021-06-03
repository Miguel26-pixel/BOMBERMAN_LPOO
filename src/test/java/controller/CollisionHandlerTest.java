package controller;

import controller.collision.*;
import model.entity.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CollisionHandlerTest {

    @Test
    public void blastch(){
        BlastController bc = Mockito.mock(BlastController.class);
        Wall w = Mockito.mock(Wall.class);
        Blast blast = Mockito.mock(Blast.class);
        BlastCollisionHandler bch = new BlastCollisionHandler(bc);
        bch.handler(w,blast);
        Mockito.verify(bc,Mockito.times(1)).stopExpanding(Mockito.any(Blast.class));
    }

    @Test
    public void bch(){
        BombController bc = Mockito.mock(BombController.class);
        Bomb b = Mockito.mock(Bomb.class);
        Blast blast = Mockito.mock(Blast.class);
        BombCollisionHandler bch = new BombCollisionHandler(bc);
        bch.handler(blast,b);
        Mockito.verify(bc,Mockito.times(1)).explodeBomb(Mockito.any(Bomb.class));
    }

    @Test
    public void bbch(){
        BomberController bc = Mockito.mock(BomberController.class);
        Bomber b = Mockito.mock(Bomber.class);
        Monster m = Mockito.mock(Monster.class);
        BomberCollisionHandler bch = new BomberCollisionHandler(bc);
        bch.handler(m,b);
        Mockito.verify(bc,Mockito.times(1)).decreaseHealth(Mockito.any(Bomber.class));
    }

    @Test
    public void mch(){
        MonsterController mc = Mockito.mock(MonsterController.class);
        Monster m = Mockito.mock(Monster.class);
        Blast blast = Mockito.mock(Blast.class);
        MonsterCollisionHandler bch = new MonsterCollisionHandler(mc);
        bch.handler(blast,m);
        Mockito.verify(mc,Mockito.times(1)).destroyMonster(Mockito.any(Monster.class));
    }

    @Test
    public void wch(){
        WallController bc = Mockito.mock(WallController.class);
        Wall w = Mockito.mock(Wall.class);
        Blast blast = Mockito.mock(Blast.class);
        WallCollisionHandler wch = new WallCollisionHandler(bc);
        wch.handler(blast,w);
        Mockito.verify(bc,Mockito.times(1)).destroyWall(Mockito.any(Wall.class));
    }
}
