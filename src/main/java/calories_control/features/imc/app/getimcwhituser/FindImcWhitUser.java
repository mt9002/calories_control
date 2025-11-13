package calories_control.features.imc.app.getimcwhituser;

import java.util.List;

import org.springframework.stereotype.Component;

import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import calories_control.features.imc.domain.IMCRepository;
import calories_control.features.imc.infra.IMCWithUserProjection;

@Component
public class FindImcWhitUser implements IGetImcWhitUser {

    private final IMCRepository imcRepository;

    public FindImcWhitUser(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    @Override
    public List<IMCWithUserProjection> findImcWhitUser() {
        Long userId = SecurityContextUtil.getUserId();
        return imcRepository.getImcWhitUser(userId);
    }

}
