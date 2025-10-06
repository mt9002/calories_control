package calories_control.features.imc.getbyall;

import java.util.List;

import calories_control.features.imc.zpersistence.IMCWithUserProjection;

public interface IGetAllUseCase {
    List<IMCWithUserProjection> getAll();
}
