package controller;

import gui.GUI;
import gui.Key;
import model.arena.Arena;
import model.entity.Bomber;
import model.player.KeySetBuilder;
import model.player.Player;
import model.player.PlayerKeySet;
import model.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerController extends GameController{
    private final BomberController bomberController;

    private List<Player> players;
    public PlayerController(Arena arena, BomberController bomberController) {
        super(arena);
        this.bomberController = bomberController;
        players = new ArrayList<>();
    }

    public void addPlayer(int n){
        Random generator = new Random();
        Player player = new Player(n);
        KeySetBuilder keySetBuilder = new KeySetBuilder();
        player.setKeyset(keySetBuilder.buildKeySet(n));
        players.add(player);
        if(arena.getInitialBombers().size() != 0) {
            int number = generator.nextInt(arena.getInitialBombers().size());
            Bomber bomber = arena.addBomber(arena.getInitialBombers().get(number).getPosition());
            bomber.setColor(n == 1 ? "#FF0000" : "#0000FF");
            player.setBomber(bomber);
            arena.getInitialBombers().remove(number);
        }
        else {
            int randomX;
            int randomY;
            do{
                randomX = generator.nextInt(arena.getWidth());
                randomY = generator.nextInt(arena.getHeight());
            }while(!arena.isEmpty(new Position(randomX,randomY)));
            Bomber bomber = arena.addBomber(new Position(randomX,randomY));
            bomber.setColor(n == 1 ? "#FF0000" : "#0000FF");
            player.setBomber(bomber);
        }

    }

    public void handleInput(Key key){
        for(Player player : players){
            PlayerKeySet keySet = player.getKeyset();
            GUI.ACTION action = keySet.getAction(key);
            if(action != null){
                bomberController.doAction(player.getBomber(), action);
            }
        }
    }

    public int getNumberPlayers(){
        return players.size();
    }

    public Player getWinner(){
        Player winner = null;
        int nAlive = 0;
        for(Player player : players){
            if(player.getBomber().getHealth() > 0){
                winner = player;
                nAlive++;
            }
        }
        if(nAlive > 1){
            return null;
        } else {
            return winner;
        }
    }



}
