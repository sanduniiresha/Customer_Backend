package lk.gedaratama.backendserver.config;

import lk.gedaratama.backendserver.util.GedaratamaParam;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sashini Tharuka on 5/31/2020.
 */


@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    public JwtTokenAuthenticationFilter(JwtAuthenticationConfig jwtAuthenticationConfig) {
        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain)
            throws ServletException, IOException {

        String token = req.getHeader(GedaratamaParam.AUTH_HEADER);
        if (token != null && token.startsWith(GedaratamaParam.JWT_PREFIX + " ")) {
            token = token.replace(GedaratamaParam.JWT_PREFIX + " ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(jwtAuthenticationConfig.getSecret().getBytes())
                        .parseClaimsJws(token)
                        .getBody();
                String username = claims.getSubject();
                List<String> authorities = claims.get("authorities", List.class);
                if (username != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
                            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignore) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(req, rsp);
    }
}