package calories_control.features.imc.getbyuser;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import calories_control.features.imc.zpersistence.IMCWithUserProjection;

@Controller
@RequestMapping("/imc")
public class GetByUserController {
    
    private final IGetByUser getByUserUseCase;

    public GetByUserController(IGetByUser getByUserUseCase) {
        this.getByUserUseCase = getByUserUseCase;
    }

    public List<IMCWithUserProjection> getByUserController() {
        return getByUserUseCase.getByUser();   
    }
}
