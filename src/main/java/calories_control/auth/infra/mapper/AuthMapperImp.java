package calories_control.auth.infra.mapper;

import calories_control.auth.application.dtos.RegisterResponse;
import calories_control.auth.domain.IAuthMapper;
import calories_control.user.domain.User;
import calories_control.user.infra.user.UserModel;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImp implements IAuthMapper {

    @Override
    public UserModel toUserModel(User user) {
        UserModel userModel = new UserModel(
                user.getName(),
                user.getEmail(),
                user.getHash());
        userModel.setRole(user.getRole());
        return userModel;
    }

    @Override
    public RegisterResponse toRegisterResponse(UserModel userModel) {
        return new RegisterResponse(
                userModel.getName(),
                userModel.getRole(),
                userModel.getEmail(),
                userModel.getId()
        );
    }
}
