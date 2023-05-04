package com.acelerati.management_service.infraestructure.config.security.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private static final String ROLE_EMPLOYEE="EMPLOYEE";
    private static final String ROLE_CLIENT="CLIENT";

    public boolean checkEmployeeRole(List<String> roles){
        return roles.contains(ROLE_EMPLOYEE);
    }
    public boolean checkClientRole(List<String> roles){
        return roles.contains(ROLE_CLIENT);
    }
    public boolean checkEmployeeOrClient(List<String> roles){
        return roles.contains(ROLE_EMPLOYEE) || roles.contains(ROLE_CLIENT) ;
    }
    public List<String> getRolesContext(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role->role.substring(5))
                .collect(Collectors.toList());
    }


}
