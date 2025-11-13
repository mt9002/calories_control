package calories_control.features.imc.domain;

public class IMCCalculator {

    public static double GetImcValue(double peso, double altura) {
        double heightMeters = altura / 100;
        double heightSquared = heightMeters * heightMeters;
        double imcValue = peso / heightSquared;
        return Math.round(imcValue * 100.0) / 100.0;
    }
}
