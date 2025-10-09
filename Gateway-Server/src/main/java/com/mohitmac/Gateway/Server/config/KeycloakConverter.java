package com.mohitmac.Gateway.Server.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakConverter implements Converter<Jwt,Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        
        // Extract from realm_access
        Map<String,Object> realmAccess=jwt.getClaim("realm_access");
        if(realmAccess!=null&&realmAccess.containsKey("roles")){
            List<String> realmroles=(List<String>)realmAccess.get("roles");
            realmroles.forEach( role->{
                System.out.println("Adding realm role: ROLE_" + role.toUpperCase());
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
            });
        }

        // Extract from resource_access
        Map<String ,Object> resourceAccess=jwt.getClaimAsMap("resource_access");
        if(resourceAccess!=null){
            resourceAccess.forEach((client,clientDetails)->{
                Map<String,Object> clientRoles=(Map<String,Object>) clientDetails;
                if(clientRoles.containsKey("roles")){
                    List<String> roles=(List<String>)clientRoles.get("roles");
                    roles.forEach(role->{
                        System.out.println("Adding client role from " + client + ": ROLE_" + role.toUpperCase());
                        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
                    });
                }
            });
        }
        
        System.out.println("Total authorities extracted: " + authorities);
        return authorities;
    }
    
}
