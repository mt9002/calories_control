package calories_control.features.imc.domain;

public class IMCCategory {

    public static String imcCategory(double imcValue) {
        if (imcValue < 18.5) {
            return "Bajo peso";
        } else if (imcValue >= 18.5 && imcValue < 25) {
            return "Normal";
        } else if (imcValue >= 25 && imcValue < 30) {
            return "Sobrepeso";
        } else if (imcValue >= 30 && imcValue < 35) {
            return "Obesidad tipo 1";
        } else if (imcValue >= 35 && imcValue < 40) {
            return "Obesidad tipo 2";
        } else {
            return "Obesidad tipo 3";
        }
    }
}
