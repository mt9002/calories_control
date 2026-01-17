package calories_control.features.auth.domain;


import calories_control.features.auth.app.register.RegisterResponse;
import calories_control.features.auth.infra.user.UserModel;

public interface IAuthMapper {
    UserModel toUserModel(User user);
    RegisterResponse toRegisterResponse(UserModel userModel);
}
