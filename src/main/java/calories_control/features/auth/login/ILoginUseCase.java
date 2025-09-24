package calories_control.features.auth.login;

import calories_control.features.shared.Result;

public interface ILoginUseCase {
    public Result login(String email, String password);
}
