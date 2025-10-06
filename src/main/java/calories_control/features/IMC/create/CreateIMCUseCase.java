package calories_control.features.imc.create;

import java.util.Optional;

import org.springframework.stereotype.Component;

import calories_control.features.auth.security.util.SecurityContextUtil;
import calories_control.features.imc.IMC;
import calories_control.features.imc.IMCCalculator;
import calories_control.features.imc.IMCCategory;
import calories_control.features.imc.IMCRepository;
import calories_control.features.shared.Result;
import calories_control.features.shared.State;

@Component
public class CreateIMCUseCase implements ICreateIMCUseCase {

    private final IMCRepository imcRepository;

    public CreateIMCUseCase(IMCRepository imcRepository) {
        this.imcRepository = imcRepository;
    }

    public Result add(double peso, double altura) {

        Long userId = SecurityContextUtil.getUserId();

        Optional<IMC> imcAdd = imcRepository.save(buildIMCEntity(peso, altura), userId);

        if (imcAdd.isPresent()) {
            return Result.success("IMC creado exitosamente");
        }
        return Result.failure("IMC no Creado", State.NOT_FOUND);
    }

    private double imcCalculator(double peso, double altura) {
        System.out.println("use case, Peso: " + peso + ", Altura: " + altura);
        return IMCCalculator.GetImcValue(peso, altura);
    }

    private IMC buildIMCEntity(double peso, double altura) {
        IMC entity = new IMC(peso, altura);
        double imcValue = imcCalculator(peso, altura);
        entity.setResultado(imcValue);
        entity.setClasificacion(IMCCategory.imcCategory(imcValue));
        System.out.println("IMC Value: " + imcValue + ", Clasificacion: " + entity.getClasificacion());
        return entity;
    }
}
