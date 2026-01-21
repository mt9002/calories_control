package calories_control.imc.domain;

import java.util.List;

import calories_control.imc.infra.IMCWithUserProjection;

public interface IMCRepository {

    public IMC save(IMC imc);

    public List<IMCWithUserProjection> getImcWhitUser(long userId);

    public List<IMC> findAll();

    IMC findById(Long id);
}
