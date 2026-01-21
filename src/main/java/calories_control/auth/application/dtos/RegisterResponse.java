package calories_control.auth.application.dtos;

import calories_control.user.infra.user.Role;

public record RegisterResponse(String name,
                               Role role,
                               String email,
                               Long userId) {
}
