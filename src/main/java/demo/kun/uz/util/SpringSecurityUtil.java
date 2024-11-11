package demo.kun.uz.util;


import demo.kun.uz.Enum.ProfileRole;
import demo.kun.uz.exps.AppForbiddenException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityUtil {

    public static void checkRoleExists(String profileRole, ProfileRole... requiredRoles) {
        for (ProfileRole requiredRole : requiredRoles) {
            if (requiredRole.name().equals(profileRole)) {
                return;
            }
        }
        throw new AppForbiddenException("Forbidden");
    }

}
