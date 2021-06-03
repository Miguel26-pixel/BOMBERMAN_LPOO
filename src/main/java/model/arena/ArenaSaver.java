package model.arena;

import controller.BoardBuilderController;

import model.entity.Bomber;
import model.entity.Wall;
import model.utils.Position;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;


public class ArenaSaver {
    private final String pathToResource = "src/main/resources/arenas";

    public ArenaSaver() {}

    public void saveArena(BoardBuilderController bbcontroller) throws IOException, URISyntaxException {
        File file = new File("arena1.txt");
        String filePath = "";
        int fileIndex = 0;
        do {
            fileIndex++;
            filePath = pathToResource + "/arena" + fileIndex + ".txt";
            file = new File(filePath);
        } while(!file.createNewFile());

        FileWriter writeToFile = new FileWriter(filePath);
        writeToFile.write(bbcontroller.getArena().getWidth() + " " + bbcontroller.getArena().getHeight() + "\n");

        for (int j = 4 ; j < bbcontroller.getArena().getHeight() + 4; j++) {
            for (int i = 18; i < bbcontroller.getArena().getWidth() + 18; i++) {
                if (bbcontroller.isEntity(new Position(i, j)) != ' ') {

                    if (bbcontroller.getEntity(new Position(i, j)) instanceof Wall)
                        writeToFile.write("#");
                    else if (bbcontroller.getEntity(new Position(i, j)) instanceof Bomber)
                        writeToFile.write("P");
                    else
                        writeToFile.write("M");

                } else {
                    writeToFile.write("-");
                }
            }
            if (j != bbcontroller.getArena().getHeight() + 3) writeToFile.write("\n");
        }
        writeToFile.close();
    }


}
