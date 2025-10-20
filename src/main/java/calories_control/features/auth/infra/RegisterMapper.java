package calories_control.features.auth.infra;

import org.mapstruct.Mapper;

import calories_control.features.auth.User;
import calories_control.features.auth.register.RequestRegisterDto;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    
    User user(RequestRegisterDto requestDto);
}
