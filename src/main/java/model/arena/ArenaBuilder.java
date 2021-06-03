package model.arena;

import model.entity.Bomber;
import model.entity.Monster;
import model.entity.Wall;
import model.utils.EntityToken;
import model.utils.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class ArenaBuilder {

    public ArenaBuilder() {

    }

    public Arena buildArena(String path){
		URL resource = getClass().getClassLoader().getResource(path);
		if(resource != null) {
			try {
	            File arenaFile = new File(resource.toURI());
	            Scanner fileReader = new Scanner(arenaFile);
	            int width = fileReader.nextInt();
	            int height = fileReader.nextInt();
	            Arena arena = new Arena(width, height);
	            int y = 0;
	            fileReader.nextLine(); // read empty line
	            while(fileReader.hasNextLine()){
	                String line = fileReader.nextLine();
	                for(int x = 0; x < line.length(); x++){
						if(line.charAt(x) == '#'){
							Wall wall = new Wall(new Position(x, y));
							arena.addEntity(wall);
						}
						else if(line.charAt(x) == 'P'){
							Bomber bomber = new Bomber(new Position(x, y));
							arena.getInitialBombers().add(bomber);
						}
						else if(line.charAt(x) == 'M'){
							Monster monster = new Monster(new Position(x, y));
							arena.getInitialMonster().add(monster);
						}

	                }
	                y++;
	                }
	                return arena;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		}

        return null;
    }


}
