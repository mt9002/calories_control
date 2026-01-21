package calories_control.user.application.dtos;

import calories_control.user.infra.user.Role;

import java.util.Date;

public record ProfileResponse(String email, Role role, Date date, Long userId) {}
