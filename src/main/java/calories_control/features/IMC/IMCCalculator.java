package calories_control.features.IMC;

public class IMCCalculator {
   
    public static double GetImcValue(double peso, double altura) {
        double heightMeters = altura/100;
        double heightSquared = heightMeters * heightMeters;
        double imcValue = peso / heightSquared;
        System.out.println("Calculator, Peso: " + peso + ", Altura: " + altura + ", IMC: " + imcValue);
        return Math.round(imcValue * 100.0) / 100.0;
    }
}
