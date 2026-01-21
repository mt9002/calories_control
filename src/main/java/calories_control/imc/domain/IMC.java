package calories_control.imc.domain;

import calories_control.imc.exception.InvalidImcValueException;

public class IMC {
    private Long id;
    private double altura;
    private double peso;
    private double resultado;
    private IMCCategory clasificacion;

    public IMC(double peso, double altura) {
        if (altura <= 0 || peso <= 0) {
            throw new InvalidImcValueException("Peso y altura deben ser mayores que cero.");
        }
        this.peso = peso;
        this.altura = altura;
        this.resultado = calculatorImc(peso, altura);
        this.clasificacion = IMCCategory.category(resultado);
    }

    public double calculatorImc(double peso, double altura){
        double heightMeters = altura / 100;
        double heightSquared = heightMeters * heightMeters;
        double imcValue = peso / heightSquared;
        return Math.round(imcValue * 100.0) / 100.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public IMCCategory getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(IMCCategory clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

}
