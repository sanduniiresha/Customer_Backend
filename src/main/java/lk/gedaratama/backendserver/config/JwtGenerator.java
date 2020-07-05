package lk.gedaratama.backendserver.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lk.gedaratama.backendserver.util.GedaratamaParam;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *  @author Sashini Tharuka on 5/31/2020.
 */
public class JwtGenerator {
    public static String generateAccessJWT(String subject, String uuid, Collection<? extends GrantedAuthority> authorities, int expiration, String secret) {
        Instant now = Instant.now();
        return Jwts.builder().setSubject(subject)
                .claim("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).claim("ud", uuid)
                .claim("type", GedaratamaParam.JWT_TOKEN_TYPE_ACCESS).compact();
    }


    public static String generateRefreshToken(String subject, String uuid, Collection<? extends GrantedAuthority> authorities, int expiration, String secret) {
        Instant now = Instant.now();
        return Jwts.builder().setSubject(subject).claim("authorities", authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).claim("ud", uuid)
                .claim("type", GedaratamaParam.JWT_TOKEN_TYPE_REFRESH).compact();
    }
}
