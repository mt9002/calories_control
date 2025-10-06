package calories_control.features.imc.zpersistence;

public interface IMCWithUserProjection {
    Long getImcId();
    Double getAltura();
    Double getPeso();
    Double getResultado();
    String getClasificacion();
    //Long getUserId();
    String getUserName();
    String getUserEmail();
}
