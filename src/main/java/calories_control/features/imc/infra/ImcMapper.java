package calories_control.features.imc.infra;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import calories_control.features.imc.domain.IMC;

@Mapper(componentModel = "spring")
public interface ImcMapper {

    ImcModel toImcModel(IMC imc);

    IMC toIMC(ImcModel imcModel);

}
