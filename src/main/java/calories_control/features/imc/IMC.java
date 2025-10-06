package calories_control.features.imc;

public class IMC {
    private double weight;
    private double height;
    private double imcValue;
    private String clasificacion;

    public IMC(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }
    
    public double getWeight() {
        return weight;
    }
    public double getHeight() {
        return height;
    }
    public double getImcValue() {
        return imcValue;
    }
    public void setResultado(double imcValue) {
        this.imcValue = imcValue;
    }
    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public void setIMC(double imc) {
        this.imcValue = imc;
    }

}
