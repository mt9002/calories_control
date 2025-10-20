package calories_control.features.auth.infra;

import org.springframework.stereotype.Component;

import calories_control.features.imc.IUserValidate;

@Component
public class UserValidateImp implements IUserValidate {

    private UserJpa userJpa;

    public UserValidateImp(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    @Override
    public boolean isGetById(Long userId) {
        return userJpa.existsById(userId);
    }
    
}
