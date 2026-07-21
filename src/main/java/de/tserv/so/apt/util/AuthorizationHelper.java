package de.tserv.so.apt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationHelper {
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return false; 
        }
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"));
    }
}
