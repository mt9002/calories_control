package calories_control.features.imc.domain;

import java.util.List;
import java.util.Optional;

import calories_control.features.imc.infra.IMCWithUserProjection;

public interface IMCRepository {

    public Optional<IMC> save(IMC imc);

    public List<IMCWithUserProjection> getImcWhitUser(long userId);
}
