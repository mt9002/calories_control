package calories_control.features.shared;

public class RegisterDtoN {
    String dia;
    String tipoComida;
    String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTipoComida() {
        return tipoComida;
    }
    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }
}
