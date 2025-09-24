package calories_control.features.IMC.getByAll;

import java.util.List;

import org.springframework.stereotype.Component;

import calories_control.features.IMC.IMCRepository;
import calories_control.features.IMC.zpersistence.IMCWithUserProjection;

@Component
public class GetAllUseCase implements IGetAllUseCase {

    private final IMCRepository imcRepository;
    public GetAllUseCase(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    public List<IMCWithUserProjection> getAll() {
        return imcRepository.getAll();
    }
}
