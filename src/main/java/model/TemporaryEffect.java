package model;

import model.utils.EffectToken;

public class TemporaryEffect {
    private EffectToken effect;
    private int ticksLeft;

    public TemporaryEffect(EffectToken effect, int duration) {
        this.effect = effect;
        this.ticksLeft = duration * 20;
    }

    public boolean isActive(){
        return ticksLeft > 0;
    }

    public void update(){
        ticksLeft--;
    }

    public EffectToken getEffect() {
        return effect;
    }
}
