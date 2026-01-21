package calories_control.imc.infra;

public interface IMCWithUserProjection {
    Long getImcId();

    Double getAltura();

    Double getPeso();

    Double getResultado();

    String getClasificacion();

    // Long getUserId();
    String getUserName();

    String getUserEmail();

    String getFechaRegistro();
}
