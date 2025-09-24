package calories_control.features.IMC.getByAll;

import java.util.List;

import calories_control.features.IMC.zpersistence.IMCWithUserProjection;

public interface IGetAllUseCase {
    List<IMCWithUserProjection> getAll();
}
