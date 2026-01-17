package calories_control.features.auth.app.register;

import calories_control.features.auth.infra.user.Role;

public record RegisterResponse(String name,
                               Role role,
                               String email,
                               Long userId) {
}
