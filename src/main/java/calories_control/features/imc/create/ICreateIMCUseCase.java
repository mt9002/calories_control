package calories_control.features.imc.create;

import calories_control.features.shared.Result;

public interface ICreateIMCUseCase {
    Result add(double peso, double altura);
}
