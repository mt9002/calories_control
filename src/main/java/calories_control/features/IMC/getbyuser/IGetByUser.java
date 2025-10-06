package calories_control.features.imc.getbyuser;

import java.util.List;

import calories_control.features.imc.zpersistence.IMCWithUserProjection;

public interface IGetByUser {
    List<IMCWithUserProjection> getByUser();
}
