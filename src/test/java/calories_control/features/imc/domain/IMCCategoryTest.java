package calories_control.features.imc.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IMCCategoryTest {

    @Test
    void testImcCategory() {

        // Arrange

        //

        // Bajo peso
        assertEquals("Bajo peso", IMCCategory.imcCategory(17.0));
        assertEquals("Bajo peso", IMCCategory.imcCategory(18.4));

        // Normal
        assertEquals("Normal", IMCCategory.imcCategory(18.5));
        assertEquals("Normal", IMCCategory.imcCategory(22.0));
        assertEquals("Normal", IMCCategory.imcCategory(24.9));

        // Sobrepeso
        assertEquals("Sobrepeso", IMCCategory.imcCategory(25.0));
        assertEquals("Sobrepeso", IMCCategory.imcCategory(27.0));
        assertEquals("Sobrepeso", IMCCategory.imcCategory(29.9));

        // Obesidad tipo 1
        assertEquals("Obesidad tipo 1", IMCCategory.imcCategory(30.0));
        assertEquals("Obesidad tipo 1", IMCCategory.imcCategory(32.0));
        assertEquals("Obesidad tipo 1", IMCCategory.imcCategory(34.9));

        // Obesidad tipo 2
        assertEquals("Obesidad tipo 2", IMCCategory.imcCategory(35.0));
        assertEquals("Obesidad tipo 2", IMCCategory.imcCategory(37.0));
        assertEquals("Obesidad tipo 2", IMCCategory.imcCategory(39.9));

        // Obesidad tipo 3
        assertEquals("Obesidad tipo 3", IMCCategory.imcCategory(40.0));
        assertEquals("Obesidad tipo 3", IMCCategory.imcCategory(50.0));
    }
}