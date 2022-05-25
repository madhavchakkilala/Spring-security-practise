package com.store.online.Ecom.Service;

import com.store.online.Ecom.Models.UserPrincipal;
import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TokenProviderService {

    private final String JWT_SECRET = "secret_word";
    private final int JWT_EXPIRATION_TIME = 60000000;

    public String generateToken(UserPrincipal userPrincipal){
        List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder().setIssuer("Fashion store").setSubject(userPrincipal.getEmail()).
                setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME)).claim("Roles",roles).signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
    }
    public boolean validate(String jwtToken){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken.substring(7));
            return true;
        } catch (SignatureException exception){
            System.out.println("Invalid token");
        } catch (MalformedJwtException e){
            System.out.println("Invalid json token formation " + e.getMessage() );
        } catch (ExpiredJwtException e){
            System.out.println("Jwt token has expired");
        } catch (UnsupportedJwtException e){
            System.out.println("this format is not supported");
        }
        return false;
    }

    public String getEmailFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken.substring(7)).getBody().getSubject();
    }
}
