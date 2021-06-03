package model.entity;

import model.TemporaryEffect;
import model.utils.EntityToken;
import model.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GenericEntity implements Entity{
    protected Position position;
    protected char character;
    protected String color;
    private List<TemporaryEffect> effects;

    public GenericEntity(Position position, EntityToken character, String color) {
        this.position = position;
        this.character = character.label;
        this.color = color;
        effects = new ArrayList<>();
    }

    @Override
    public void update() {
        for(TemporaryEffect effect : effects){
            effect.update();
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public char getCharacter() {
        return character;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public List<TemporaryEffect> getEffects() {
        return effects;
    }

    @Override
    public void addEffect(TemporaryEffect effect) {
        effects.add(effect);
    }
}
