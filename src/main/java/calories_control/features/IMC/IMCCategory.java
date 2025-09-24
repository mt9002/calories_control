package calories_control.features.IMC;

public class IMCCategory {

    public static String imcCategory(double imcValue) {
        if (imcValue < 18.5) {
            return "Bajo peso";
        } else if (imcValue >= 18.5 && imcValue < 24.9) {
            return "Normal";
        } else if (imcValue >= 25 && imcValue < 29.9) {
            return "Sobrepeso";
        } else if (imcValue >= 30 && imcValue < 34.9) {
            return "Obesidad tipo 1";
        } else if (imcValue >= 35 && imcValue < 39.9) {
            return "Obesidad tipo 2";
        } else {
            return "Obesidad tipo 3";
        }
    }
}
