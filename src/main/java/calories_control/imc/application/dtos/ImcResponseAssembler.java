package calories_control.imc.application.dtos;


import calories_control.imc.application.controller.ImcController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ImcResponseAssembler {

    public ImcResponse toModel(ImcResponse response) {
        response.add(
                linkTo(methodOn(ImcController.class)
                        .findAllIMC())
                        .withRel("all-imc")
        );
        response.add(
                linkTo(methodOn(ImcController.class)
                        .findById(response.getId()))
                        .withSelfRel()
        );
        return response;
    }
}
