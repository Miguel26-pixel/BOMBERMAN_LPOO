package gui;

import model.entity.Entity;
import model.utils.Position;

import java.io.IOException;
import java.util.List;
import java.awt.*;

public interface GUI {
    void drawAll(List<Entity> entities, int offsetX, int offsetY,int arenaHeight, int arenaWidth);
    void drawEntity(Entity entity, int offsetX, int offsetY);

    void drawCharacter(Position position,char character, String color);

    void drawString(Position position, String str, String color);

    int getWidth();

    int getHeight();

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    Key getKey() throws IOException;
    ACTION getNextAction(boolean isPoll) throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, PLACEBOMB, MODES, PLAYER, MONSTER};
}
