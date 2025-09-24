package calories_control.features.IMC.get_by_user;

import java.util.List;

import org.springframework.stereotype.Component;

import calories_control.features.IMC.IMCRepository;
import calories_control.features.IMC.zpersistence.IMCWithUserProjection;
import calories_control.features.auth.security.util.SecurityContextUtil;

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
