package jrvsoft.ppmtool.security;

import io.jsonwebtoken.*;
import jrvsoft.ppmtool.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static jrvsoft.ppmtool.security.SecurityConstants.EXPIRATION_TIME;
import static jrvsoft.ppmtool.security.SecurityConstants.SECRET;

@Component
@Slf4j
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parse(token);
            return  true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT Claims is empty");
        }
        return  false;
    }

    // get user ID from token
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id  = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
