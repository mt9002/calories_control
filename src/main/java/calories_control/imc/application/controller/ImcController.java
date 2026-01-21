package calories_control.imc.application.controller;

import calories_control.imc.application.dtos.ImcResponseAssembler;
import calories_control.imc.application.service.ImcService;
import calories_control.imc.application.dtos.ImcResponse;
import calories_control.imc.domain.IMC;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/imc")
public class ImcController {

    private final ImcService imcService;
    private final ImcResponseAssembler assembler;

    public ImcController(ImcService imcService, ImcResponseAssembler assembler) {
        this.imcService = imcService;
        this.assembler = assembler;
    }

    @PostMapping("/create")
    public ImcResponse createIMC(
            @RequestParam("peso") double peso,
            @RequestParam("altura") double altura) {
        ImcResponse imcResponse = imcService.createImc2(peso, altura);
        // Hateoas
        imcResponse.add(
                linkTo(methodOn(ImcController.class)
                        .createIMC(peso, altura))
                        .withRel("create")
        );
        return assembler.toModel(imcResponse);
    }

    @GetMapping("/findAll")
    public List<IMC> findAllIMC(){
        return imcService.findAll();
    }

    @GetMapping("/findById")
    public IMC findById(@RequestParam(value = "id") Long id){
        return imcService.findById(id);
    }

    @GetMapping
    public String showRegisterForm(){
        imcService.findImcWhitUser();
        return "";

    }
}
