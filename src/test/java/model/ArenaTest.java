package model;
import model.arena.Arena;
import model.entity.*;
import model.utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ArenaTest {
    private Arena arena;
    private Bomber bomber;
    private Monster monster;
    private Bomb bomb;
    private Blast blast;
    private Wall wall;

    @BeforeEach
    void setUp(){
        arena = new Arena(10,10);
        bomber = Mockito.mock(Bomber.class);
        monster = Mockito.mock(Monster.class);
        bomb = Mockito.mock(Bomb.class);
        blast = Mockito.mock(Blast.class);
        wall = Mockito.mock(Wall.class);
    }

    @Test
    public void AddEntities(){
        List<Entity> res = new ArrayList<>();
        Assertions.assertEquals(0,arena.getEntities().size());
        arena.addEntity(bomber);
        res.add(bomber);
        Assertions.assertEquals(res,arena.getEntities());
        arena.addEntity(monster);
        res.add(monster);
        Assertions.assertEquals(res,arena.getEntities());
        arena.addEntity(bomb);
        res.add(bomb);
        Assertions.assertEquals(res,arena.getEntities());
        arena.addEntity(blast);
        res.add(blast);
        Assertions.assertEquals(res,arena.getEntities());

        Assertions.assertEquals(1,arena.getBombs().size());
        arena.addBomb(new Position(0,0),1,bomber);
        Assertions.assertEquals(2,arena.getBombs().size());
        Assertions.assertEquals(5,arena.getEntities().size());
        arena.addBlast(blast);
        Assertions.assertEquals(2,arena.getBlasts().size());
        Assertions.assertEquals(6,arena.getEntities().size());

        arena.addBomber(new Position(1,1));
        Assertions.assertEquals(7,arena.getEntities().size());

        arena.addMonster(0,0);
        Assertions.assertEquals(2,arena.getMonsters().size());
        Assertions.assertEquals(8,arena.getEntities().size());
    }

    @Test
    public void removeEntities(){
        Assertions.assertEquals(0,arena.getEntities().size());
        arena.addEntity(bomber);
        arena.addEntity(monster);
        arena.addEntity(bomb);
        arena.addEntity(blast);
        arena.addEntity(wall);
        Assertions.assertEquals(5,arena.getEntities().size());
        arena.removeBlast(blast);
        Assertions.assertEquals(4,arena.getEntities().size());
        Assertions.assertEquals(0,arena.getBlasts().size());
        arena.removeBomb(bomb);
        Assertions.assertEquals(3,arena.getEntities().size());
        arena.removeWall(wall);
        Assertions.assertEquals(2,arena.getEntities().size());
        arena.removeMonster(monster);
        Assertions.assertEquals(0,arena.getMonsters().size());
        Assertions.assertEquals(1,arena.getEntities().size());
    }

    @Test
    public void TestPosition(){
        for(int x=0;x<10;x++) {
            for (int y = 0; y < 10; y++) {
                Assertions.assertEquals(true, arena.isEmpty(new Position(x, y)));
            }
        }
        arena.addBomb(new Position(1,2),0,bomber);
        for(int x=0;x<10;x++) {
            for (int y = 0; y < 10; y++) {
                if(x==1 && y==2){
                    Assertions.assertEquals(false, arena.isEmpty(new Position(x, y)));
                }
                else{
                    Assertions.assertEquals(true, arena.isEmpty(new Position(x, y)));
                }
            }
        }
    }

    @Test
    public void Getters(){
        Assertions.assertEquals(10,arena.getHeight());
        Assertions.assertEquals(10,arena.getWidth());

        List<Entity> nv = new ArrayList<>();
        Assertions.assertEquals(nv,arena.getMonsters());
        Assertions.assertEquals(null,arena.getMonster(0));
        Assertions.assertEquals(null,arena.getMonster(1));
        Assertions.assertEquals(null,arena.getMonster(-1));
        arena.addMonster(0,0);
        Assertions.assertEquals(1,arena.getMonsters().size());
        Assertions.assertEquals(null,arena.getMonster(-1));
        Assertions.assertFalse(null==arena.getMonster(0));
        Assertions.assertEquals(null,arena.getMonster(1));

        arena.addBomber(new Position(1,2));
        Assertions.assertFalse(null==arena.getBomber(0));
    }
}
