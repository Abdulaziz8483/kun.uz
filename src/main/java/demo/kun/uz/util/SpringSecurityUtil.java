package demo.kun.uz.util;





import demo.kun.uz.Enum.ProfileRole;
import demo.kun.uz.config.CustomUserDetails;
import demo.kun.uz.exps.AppForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {

    public static void checkRoleExists(String profileRole, ProfileRole... requiredRoles) {
        for (ProfileRole requiredRole : requiredRoles) {
            if (requiredRole.name().equals(profileRole)) {
                return;
            }
        }
        throw new AppForbiddenException("Forbidden");
    }

    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getId();
    }

}

