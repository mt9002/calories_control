package calories_control.user.infra.user;

import org.springframework.stereotype.Component;

import calories_control.user.domain.IUserValidate;

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
