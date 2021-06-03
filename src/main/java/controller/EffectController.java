package controller;

import model.TemporaryEffect;
import model.arena.Arena;
import model.entity.Entity;
import model.entity.GenericEntity;
import model.utils.EffectToken;

import java.util.ArrayList;
import java.util.List;

public class EffectController extends GameController{

    public EffectController(Arena arena) {
        super(arena);
    }

    public boolean isInvulnerable(Entity entity){
        List<TemporaryEffect> effects = entity.getEffects();
        for(TemporaryEffect effect : effects) {
            if (effect.isActive() && effect.getEffect() == EffectToken.INVULNERABILITY) {
                return true;
            }
        }
        return false;
    }

    public void addEffect(EffectToken effectToken, int duration, Entity entity){
        TemporaryEffect effect = new TemporaryEffect(effectToken, 2);
        entity.addEffect(effect);
    }



    public void addInvulnerability(int duration, Entity entity){
        addEffect(EffectToken.INVULNERABILITY, duration, entity);
    }
}
