package calories_control.user.application.service;

import calories_control.auth.infra.security.util.SecurityContextUtil;
import calories_control.user.application.dtos.ProfileResponse;
import calories_control.user.infra.user.UserModel;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public ProfileResponse viewProfile(){
        UserModel user = SecurityContextUtil.getUser();

        if (user == null){
            return null;
        }
        return new ProfileResponse(
                user.getEmail(),
                user.getRole(),
                user.getFechaRegistro(),
                user.getId());
    }

}
