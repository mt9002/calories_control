package calories_control.features.auth.app.register;

import calories_control.features.shared.Result;

public interface IRegisterAuthUserCase {

    Result execute(RequestRegisterDto register);

}
