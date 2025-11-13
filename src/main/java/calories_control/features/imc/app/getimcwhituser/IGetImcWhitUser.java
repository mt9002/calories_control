package calories_control.features.imc.app.getimcwhituser;

import java.util.List;

import calories_control.features.imc.infra.IMCWithUserProjection;

public interface IGetImcWhitUser {
    List<IMCWithUserProjection> findImcWhitUser();
}
