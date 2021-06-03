package model;
import model.TemporaryEffect;
import model.utils.EffectToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TemporaryEffectTest {
    private TemporaryEffect te;

    @BeforeEach
    void setUp() {
        te = new TemporaryEffect(EffectToken.INVULNERABILITY,1);
    }

    @Test
    public void TestActive(){
        for(int i = 0; i<10;i++){
            te.update();
        }
        Assertions.assertEquals(true,te.isActive());
        for(int i = 0; i<10;i++){
            te.update();
        }
        Assertions.assertEquals(false,te.isActive());
        te.update();
        Assertions.assertEquals(false,te.isActive());
    }
}
