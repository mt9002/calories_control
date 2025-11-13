package calories_control.features.imc.domain;

public class IMC {
    private double altura;
    private double peso;
    private double resultado;
    private String clasificacion;

    public IMC(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

}
