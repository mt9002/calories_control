package calories_control.features.auth.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import calories_control.features.auth.zpersistence.UserModel;

public class SecurityContextUtil {

    public static Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        return ((UserModel) auth.getPrincipal()).getId();
    }

    public static UserModel getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        return (UserModel) auth.getPrincipal();
    }
}
