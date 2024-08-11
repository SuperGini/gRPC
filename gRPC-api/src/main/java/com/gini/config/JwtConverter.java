package com.gini.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class JwtConverter implements Converter<Jwt, JwtAuthenticationToken> {

    @Override
    public JwtAuthenticationToken convert(Jwt source) {

        var authorities = (List<String>) source.getClaims().get("userRoles");

        var roles = authorities.stream()
                .map(x -> new SimpleGrantedAuthority("ROLE_" + x))
                .toList();

        return new JwtAuthenticationToken(source, roles);
    }
}
