package calories_control.features.IMC.get_by_user;

import java.util.List;

import calories_control.features.IMC.zpersistence.IMCWithUserProjection;

public interface IGetByUser {
    List<IMCWithUserProjection> getByUser();
}
