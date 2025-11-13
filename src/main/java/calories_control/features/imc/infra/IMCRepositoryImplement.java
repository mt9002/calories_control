
package calories_control.features.imc.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import calories_control.features.imc.domain.IMC;
import calories_control.features.imc.domain.IMCRepository;

@Repository
public class IMCRepositoryImplement implements IMCRepository {

    private final ImcJPA imcJPA;

    private final ImcMapper imcMapper;

    public IMCRepositoryImplement(ImcJPA imcJPA, ImcMapper imcMapper) {
        this.imcJPA = imcJPA;
        this.imcMapper = imcMapper;
    }

    @Override
    public Optional<IMC> save(IMC imc) {
        if (imc == null)
            return Optional.empty();

        ImcModel saveImc = imcJPA.save(imcMapper.toImcModel(imc));

        if (saveImc != null && saveImc.getId() != null) {
            return Optional.of(imcMapper.toIMC(saveImc));
        }

        return Optional.empty();

    }

    @Override
    public List<IMCWithUserProjection> getImcWhitUser(long userId) {
        Optional<List<IMCWithUserProjection>> imcList = imcJPA.findIMCByUserId(userId);
        return imcList.get();
    }

}