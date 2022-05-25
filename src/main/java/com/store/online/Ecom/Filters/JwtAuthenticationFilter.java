package com.store.online.Ecom.Filters;

import com.store.online.Ecom.Models.UserPrincipal;
import com.store.online.Ecom.Service.TokenProviderService;
import com.store.online.Ecom.Service.UserService;
import com.store.online.Ecom.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProviderService tokenProviderService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = request.getHeader("Authorization");
            if (!StringUtils.isEmpty(jwtToken) && tokenProviderService.validate(jwtToken)) {
                String emailInToken = tokenProviderService.getEmailFromToken(jwtToken);
                request.setAttribute("userEmail",emailInToken);
                UserPrincipal userPrincipal = userService.loadUserByUsername(emailInToken);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch(Exception e){
            System.out.println("Failed to validate the token "+ e);
        }
        filterChain.doFilter(request,response);
    }

}
