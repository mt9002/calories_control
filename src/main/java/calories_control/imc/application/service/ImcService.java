package calories_control.imc.application.service;

import calories_control.auth.infra.security.util.SecurityContextUtil;
import calories_control.imc.application.dtos.ImcResponse;
import calories_control.imc.domain.IMC;
import calories_control.imc.domain.IMCCategory;
import calories_control.imc.domain.IMCRepository;
import calories_control.imc.infra.IMCWithUserProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImcService {
    private final IMCRepository imcRepository;

    public ImcService(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    // CREATE IMC
    public ImcResponse createImc2(double peso, double altura) {
        System.out.println("Entrando a createImc2");
        IMC imc = imcRepository.save(toImc(peso, altura));
        System.out.println(imc.getId());
        return new ImcResponse(
                imc.getId(),
                imc.getResultado(),
                imc.getClasificacion());
    }

    // Find IMC
    public List<IMCWithUserProjection> findImcWhitUser() {
        Long userId = SecurityContextUtil.getUserId();
        return imcRepository.getImcWhitUser(userId);
    }
    // Find All
    public List<IMC> findAll(){
        return imcRepository.findAll();
    }

    private IMC toImc(double peso, double altura) {
        System.out.println("Entrando a toImc");
        return new IMC(peso, altura);
    }

    public IMC findById(Long id) {
        return imcRepository.findById(id);
    }
}
