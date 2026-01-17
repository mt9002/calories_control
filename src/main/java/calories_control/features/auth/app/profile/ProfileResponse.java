package calories_control.features.auth.app.profile;

import calories_control.features.auth.infra.user.Role;

import java.util.Date;

public record ProfileResponse(String email, Role role, Date date, Long userId) {}
