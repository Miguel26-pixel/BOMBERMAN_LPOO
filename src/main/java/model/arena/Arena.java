package model.arena;


import model.entity.*;
import model.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private List<Entity> entities;
    private List<Bomber> bombers;
    private List<Monster> monsters;
    private List<Bomb> bombs;
    private List<Blast> blasts;
    private List<Monster> initialMonsters;
    private List<Bomber> initialBombers;

    private int width, height;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        entities = new ArrayList<>();
        bombers = new ArrayList<>();
        monsters = new ArrayList<>();
        bombs = new ArrayList<>();
        blasts = new ArrayList<>();
        initialBombers = new ArrayList<>();
        initialMonsters = new ArrayList<>();

    }

    public void update(){

    }

    public boolean isEmpty(Position position){
        for(Entity entity : entities){
            if(entity.getPosition().equals(position) & entity.getClass() != Bomber.class){
                return false;
            }
        }
        return true;
    }

    public Class<? extends Entity> isWhat(Position position){
        Entity entity = getEntity(position);
        if(entity == null)
            return null;
        else return entity.getClass();
    }

    public Entity getEntity(Position position){
        for(Entity entity : entities){
            if(entity.getPosition().equals(position)){
                return entity;
            }
        }
        return null;
    }
    public Bomber addBomber(Position position){
        Bomber bomber = new Bomber(position);

        addEntity(bomber);
        return bomber;
    }

    public void generateMonster(int nr_monsters){
        Random gerador = new Random();
        if(getInitialMonster().size() != 0){
            for (int i = 0 ; i < getInitialMonster().size(); i++) {
                addMonster(getInitialMonster().get(i).getPosition().getX(),getInitialMonster().get(i).getPosition().getY());
            }
        }
        else {
            for (int i = 0; i < nr_monsters; i++) {
                int randomX;
                int randomY;
                do {
                    randomX = gerador.nextInt(getWidth());
                    randomY = gerador.nextInt(getHeight());
                    System.out.println(getHeight());
                } while (!isEmpty(new Position(randomX, randomY)));
                addMonster(randomX, randomY);
            }

        }
    }

    public void addMonster(int x, int y){
        Monster monster = new Monster(new Position(x, y));

        addEntity(monster);
    }

    public void addBomb(Position position, int fuse, Bomber bomber){
        Bomb bomb = new Bomb(position, fuse, bomber);
        bomb.setColor(bomber.getColor());
        //bombs.add(bomb);
        addEntity(bomb);
    }

    public void addBlast(Blast blast){
        //blasts.add(blast);
        addEntity(blast);
    }

    public void removeEntity(Entity entity){
        if(entity instanceof Wall){
            entities.remove(entity);
        } else if(entity instanceof Bomb){
            removeBomb((Bomb) entity);
        } else if(entity instanceof Blast){
            removeBlast((Blast) entity);
        } else if(entity instanceof Monster){
            removeMonster((Monster) entity);
        } else {
            entities.remove(entity);
        }
    }

    public void removeWall(Wall wall){
        entities.remove(wall);

    }
    public void removeBomb(Bomb bomb){
        bombs.remove(bomb);
        entities.remove(bomb);
    }
    public void removeBlast(Blast blast){
        blasts.remove(blast);
        entities.remove(blast);
    }
    public void removeMonster(Monster monster){
        monsters.remove(monster);
        entities.remove(monster);
    }
    public void addEntity(Entity entity){
        if(entity instanceof Bomb){
            bombs.add((Bomb) entity);
        } else if(entity instanceof Blast){
            blasts.add((Blast) entity);
        } else if(entity instanceof Monster){
            monsters.add((Monster) entity);
        } else if(entity instanceof Bomber){
            bombers.add((Bomber) entity);
        }
        entities.add(entity);
    }

    public List<Entity> getEntities(){
        return entities ;
    }

    public List<Bomber> getBombers(){
        return bombers ;
    }

    public List<Blast> getBlasts() {
        return blasts;
    }

    public List<Monster> getInitialMonster() {
        return initialMonsters;
    }

    public List<Bomber> getInitialBombers() {
        return initialBombers;
    }

    public Bomber getBomber(int nBomber){
        return nBomber < bombers.size() ? bombers.get(nBomber) : null;
    }

    public Monster getMonster(int nMonster){
        if(nMonster >= monsters.size() || nMonster < 0) return null;
        return monsters.get(nMonster);
    }

    public List<Monster> getMonsters(){
        return monsters;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

}
