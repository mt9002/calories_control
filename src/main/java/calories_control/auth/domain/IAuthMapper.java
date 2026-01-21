package calories_control.auth.domain;


import calories_control.auth.application.dtos.RegisterResponse;
import calories_control.user.domain.User;
import calories_control.user.infra.user.UserModel;

public interface IAuthMapper {
    UserModel toUserModel(User user);
    RegisterResponse toRegisterResponse(UserModel userModel);
}
