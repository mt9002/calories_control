package calories_control.features.imc.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IMCCalculatorTest {

    @Test
    void testGetImcValue() {
        // Test case 1: Normal BMI
        double result1 = IMCCalculator.GetImcValue(70.0, 170.0);
        assertEquals(24.22, result1);

        // Test case 2: Underweight
        double result2 = IMCCalculator.GetImcValue(50.0, 170.0);
        assertEquals(17.3, result2);

        // Test case 3: Overweight
        double result3 = IMCCalculator.GetImcValue(80.0, 170.0);
        assertEquals(27.68, result3);

        // Test case 4: Obese
        double result4 = IMCCalculator.GetImcValue(100.0, 170.0);
        assertEquals(34.6, result4);

        // Test case 5: Edge case with height in cm
        double result5 = IMCCalculator.GetImcValue(60.0, 160.0);
        assertEquals(23.44, result5);
    }
}