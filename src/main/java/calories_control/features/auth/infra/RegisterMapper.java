package calories_control.features.auth.infra;

import org.mapstruct.Mapper;

import calories_control.features.auth.app.register.RequestRegisterDto;
import calories_control.features.auth.domain.User;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    User user(RequestRegisterDto requestDto);
}
