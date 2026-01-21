package calories_control.imc.application.dtos;

import calories_control.imc.domain.IMCCategory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

public class ImcResponse extends RepresentationModel<ImcResponse> {

    private Long id;
    private double imcValue;
    private IMCCategory categories;

    public ImcResponse(Long id, double imcValue, IMCCategory categories) {
        this.imcValue = imcValue;
        this.categories = categories;
        this.id = id;
    }

    public double getImcValue() {
        return imcValue;
    }

    public void setImcValue(double imcValue) {
        this.imcValue = imcValue;
    }

    public IMCCategory getCategories() {
        return categories;
    }

    public void setCategories(IMCCategory categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
