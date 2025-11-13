package calories_control.features.imc.app.create;

import java.util.Optional;

import org.springframework.stereotype.Component;

import calories_control.features.auth.infra.security.util.SecurityContextUtil;
import calories_control.features.imc.domain.IMC;
import calories_control.features.imc.domain.IMCCalculator;
import calories_control.features.imc.domain.IMCCategory;
import calories_control.features.imc.domain.IMCRepository;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

@Component
public class CreateIMCUseCase implements ICreateIMCUseCase {

    private final IMCRepository imcRepository;

    public CreateIMCUseCase(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    public Result<IMC> add(double peso, double altura) {

        Optional<IMC> imcAdd = imcRepository.save(buildIMCEntity(peso, altura));

        if (imcAdd.isPresent()) {
            return Result.success("IMC creado exitosamente");
        }
        return Result.failure("IMC no Creado", State.NOT_FOUND);
    }

    private double imcCalculator(double peso, double altura) {
        return IMCCalculator.GetImcValue(peso, altura);

    }

    private IMC buildIMCEntity(double peso, double altura) {
        if (altura == 0 || peso == 0) {

        }
        IMC entity = new IMC(peso, altura);
        double imcValue = imcCalculator(peso, altura);
        entity.setResultado(imcValue);
        entity.setClasificacion(IMCCategory.imcCategory(imcValue));
        return entity;
    }
}
