package lk.gedaratama.backendserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Sashini Tharuka on 5/31/2020.
 */

@Component
public class JwtAuthenticationConfig implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.expiration}")
    private int accessTokenExpiration;

    @Value("${jwt.refresh.expiration}")
    private int refreshTokenExpiration;

    public int getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public int getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public String getSecret() {
        return secret;
    }
}
