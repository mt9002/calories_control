package calories_control.features.IMC.create;

import calories_control.features.shared.Result;

public interface ICreateIMCUseCase {
    Result add(double peso, double altura);
}
