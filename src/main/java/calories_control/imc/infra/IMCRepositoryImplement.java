
package calories_control.imc.infra;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import calories_control.auth.infra.security.util.SecurityContextUtil;
import calories_control.imc.domain.IMC;
import calories_control.imc.domain.IMCRepository;

@Repository
public class IMCRepositoryImplement implements IMCRepository {

    private final ImcJPA imcJPA;

    private final ImcMapper imcMapper;

    public IMCRepositoryImplement(ImcJPA imcJPA, ImcMapper imcMapper) {
        this.imcJPA = imcJPA;
        this.imcMapper = imcMapper;
    }

    @Override
    public IMC save(IMC imc) {
        ImcModel toImcModel = imcMapper.toImcModel(imc);
        toImcModel.setUserId(SecurityContextUtil.getUserId());
        ImcModel saveImc = imcJPA.save(toImcModel);
        return imcMapper.toIMC(saveImc);
    }

    @Override
    public List<IMCWithUserProjection> getImcWhitUser(long userId) {
        Optional<List<IMCWithUserProjection>> imcList = imcJPA.findIMCByUserId(userId);
        return imcList.get();
    }

    @Override
    public List<IMC> findAll() {
        return imcMapper.toIMCList(imcJPA.findAll());
    }

    @Override
    public IMC findById(Long id) {
        return imcMapper.toIMC(imcJPA.findById(id).get());
    }

}