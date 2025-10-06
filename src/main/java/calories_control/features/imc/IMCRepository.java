package calories_control.features.imc;

import java.util.List;
import java.util.Optional;

import calories_control.features.imc.zpersistence.IMCWithUserProjection;

public interface IMCRepository {
    public Optional<IMC> save(IMC imc, long userId);
    public void update(IMC imc);
    public void delete(int id);
    public IMCWithUserProjection getById(long id);
    public List<IMCWithUserProjection> getAll();
    public List<IMCWithUserProjection> getByUser(long userId);
}
