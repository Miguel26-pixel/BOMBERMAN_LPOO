package model.entity;

import model.TemporaryEffect;
import model.utils.Position;

import java.util.List;

public interface Entity {
    public void update();
    public Position getPosition();
    public void setPosition(Position position);
    char getCharacter();
    String getColor();
    void setColor(String color);
    List<TemporaryEffect> getEffects();
    void addEffect(TemporaryEffect effect);
}
