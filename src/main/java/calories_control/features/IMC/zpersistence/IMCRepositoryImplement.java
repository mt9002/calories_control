
package calories_control.features.IMC.zpersistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import calories_control.features.IMC.IMC;
import calories_control.features.IMC.IMCRepository;

@Repository
public class IMCRepositoryImplement implements IMCRepository {

    private final ImcJPA imcJPA;

    public IMCRepositoryImplement(ImcJPA imcJPA) {
        this.imcJPA = imcJPA;
    }

    @Override
    public IMCWithUserProjection getById(long id) {
        IMCWithUserProjection imc = imcJPA.findIMCById(id);
        System.out.println(imc);
        return imc;
    }

    @Override
    public Optional<IMC> save(IMC imc, long userId) {
        
        ImcModel imcModel = new ImcModel(imc.getWeight(),
                imc.getHeight(),
                imc.getImcValue(),
                userId);
        imcModel.setClasificacion(imc.getClasificacion());

        if (imc != null) {
            ImcModel imcMod = imcJPA.save(imcModel);
            System.out.println(imcMod.getId());
            return Optional.of(imc);
        }
        return Optional.empty();
    }

    

    @Override
    public void update(IMC imc) {
        // JPQL -> Java Persistence Query Language

    }

    @Override
    public void delete(int id) {
        // JPQL -> Java Persistence Query Language
    }

    @Override
    public List<IMCWithUserProjection> getAll() {
        List<IMCWithUserProjection> imcList = imcJPA.findIMCAll();
        System.out.println(imcList);
        return imcList;
    }

    @Override
    public List<IMCWithUserProjection> getByUser(long userId) {
        List<IMCWithUserProjection> imcList = imcJPA.findIMCByUserId(userId);
        return imcList;
    }

}