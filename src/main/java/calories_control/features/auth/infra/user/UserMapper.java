package calories_control.features.auth.infra.user;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import calories_control.features.auth.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserModel toUserModel(User user);

    User toUser(UserModel userModel);

    List<User> toListUser(List<UserModel> userModels);

    List<UserModel> toListUserModel(List<User> users);
}
