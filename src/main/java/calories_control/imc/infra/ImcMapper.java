package calories_control.imc.infra;

import calories_control.imc.domain.IMCCategory;
import org.mapstruct.Mapper;

import calories_control.imc.domain.IMC;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImcMapper {

    ImcModel toImcModel(IMC imc);

    default IMC toIMC(ImcModel imcModel) {
        if (imcModel == null) return null;
        IMC imc = new IMC(
                imcModel.getPeso(),
                imcModel.getAltura()
        );
        imc.setId(imcModel.getId());
        return imc;

    }

    List<IMC> toIMCList(List<ImcModel> entities);
}