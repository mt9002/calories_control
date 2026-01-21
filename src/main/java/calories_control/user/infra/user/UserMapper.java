package calories_control.user.infra.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import calories_control.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserModel toUserModel(User user);

    User toUser(UserModel userModel);

    List<User> toListUser(List<UserModel> userModels);

    List<UserModel> toListUserModel(List<User> users);
}
