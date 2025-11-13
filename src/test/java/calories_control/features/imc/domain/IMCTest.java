package calories_control.features.imc.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IMCTest {

    @Test
    void testConstructor() {
        IMC imc = new IMC(70.0, 170.0);
        assertEquals(70.0, imc.getPeso());
        assertEquals(170.0, imc.getAltura());
    }

    @Test
    void testGettersAndSetters() {
        IMC imc = new IMC(0, 0);

        imc.setPeso(75.0);
        assertEquals(75.0, imc.getPeso());

        imc.setAltura(180.0);
        assertEquals(180.0, imc.getAltura());

        imc.setResultado(22.5);
        assertEquals(22.5, imc.getResultado());

        imc.setClasificacion("Normal");
        assertEquals("Normal", imc.getClasificacion());
    }
}