package calories_control.features.auth.infra;

import java.util.List;

import org.mapstruct.Mapper;

import calories_control.features.auth.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserModel toUserModel(User user);
    User toUser(UserModel userModel);
    List<User> toListUser(List<UserModel> userModels);
    List<UserModel> toListUserModel(List<User> users);
}
