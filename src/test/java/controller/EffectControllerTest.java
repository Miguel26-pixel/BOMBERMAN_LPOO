package controller;
import controller.EffectController;
import model.TemporaryEffect;
import model.arena.Arena;
import model.entity.Entity;
import model.utils.EffectToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class EffectControllerTest {
    private EffectController effectController;
    private Entity entity;
    private TemporaryEffect temporaryEffect;
    private Arena arena;

    @BeforeEach
    void setUp(){
        temporaryEffect = Mockito.mock(TemporaryEffect.class);
        entity = Mockito.mock(Entity.class);
        arena = Mockito.mock(Arena.class);
        effectController = new EffectController(arena);
    }

    @Test
    public void InvulnerableTest(){
        Mockito.when(entity.getEffects()).thenReturn(Arrays.asList(new TemporaryEffect[]{temporaryEffect}));
        Mockito.when(temporaryEffect.getEffect()).thenReturn(EffectToken.INVULNERABILITY);
        Mockito.when(temporaryEffect.isActive()).thenReturn(true).thenReturn(false);
        Assertions.assertEquals(true,effectController.isInvulnerable(entity));
        Assertions.assertEquals(false,effectController.isInvulnerable(entity));
    }

    @Test
    public void addEffectTest(){
        effectController.addEffect(EffectToken.INVULNERABILITY,10,entity);
        Mockito.verify(entity,Mockito.times(1)).addEffect(Mockito.any());
    }
}
