package calories_control.imc.infra;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "imc")
public class ImcModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "resultado")
    private Double resultado;

    @Column(name = "clasificacion")
    private String clasificacion;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "fecha_registro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date fechaRegistro;

    public ImcModel() {
    }

    public ImcModel(Double peso, Double altura, Double resultado, Long userId) {
        this.peso = peso;
        this.altura = altura;
        this.resultado = resultado;
        this.userId = userId;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    @PrePersist
    public void setFechaRegistro() {
        this.fechaRegistro = new Date();
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getResultado() {
        return resultado;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
