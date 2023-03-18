package com.acelerati.management_service.infraestructure.config.security.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    public boolean checkEmployeeRole(List<String> roles){
        return roles.contains("EMPLOYEE");
    }
    public boolean checkEmployeeOrAdmin(List<String> roles){
        return roles.contains("EMPLOYEE") ||roles.contains("ADMIN") ;
    }
    public List<String> getRolesContext(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role->role.substring(5))
                .collect(Collectors.toList());
    }

}
