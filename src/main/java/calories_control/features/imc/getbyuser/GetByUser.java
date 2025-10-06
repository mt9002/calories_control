package calories_control.features.imc.getbyuser;

import java.util.List;

import org.springframework.stereotype.Component;

import calories_control.features.auth.security.util.SecurityContextUtil;
import calories_control.features.imc.IMCRepository;
import calories_control.features.imc.zpersistence.IMCWithUserProjection;

@Component
public class GetByUser implements IGetByUser {

    private final IMCRepository imcRepository;

    public GetByUser(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    @Override
    public List<IMCWithUserProjection> getByUser() {
        Long userId = SecurityContextUtil.getUserId();
        return imcRepository.getByUser(userId);
    }
    
}
