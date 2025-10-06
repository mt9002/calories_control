package calories_control.features.imc.getbyid;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import calories_control.features.imc.IMCRepository;

@Controller
@RequestMapping("/imc")
public class getByIdController {

    private final IMCRepository imcRepository;

    public getByIdController(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }
 
    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam(value = "id") long id) {
        return ResponseEntity.ok().body(imcRepository.getById(id)) ;
    }    

}
