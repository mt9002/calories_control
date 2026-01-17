package calories_control.features.auth.app.profile;

import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import calories_control.features.auth.infra.user.UserModel;
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
