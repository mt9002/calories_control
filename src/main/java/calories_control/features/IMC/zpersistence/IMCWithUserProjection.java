package calories_control.features.IMC.zpersistence;

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
