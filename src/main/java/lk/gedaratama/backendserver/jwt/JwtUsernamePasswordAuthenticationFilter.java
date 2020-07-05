package lk.gedaratama.backendserver.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.gedaratama.backendserver.config.JwtAuthenticationConfig;
import lk.gedaratama.backendserver.config.JwtGenerator;
import lk.gedaratama.backendserver.resource.UserResource;
import lk.gedaratama.backendserver.resource.UserCustomDetail;
import lk.gedaratama.backendserver.util.GedaratamaParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.google.gson.JsonObject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;

/**
 *  @author Sashini Tharuka on 5/31/2020.
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JwtAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    private final ObjectMapper mapper;

    public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig jwtAuthenticationConfig, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(GedaratamaParam.LOGIN_URL, "POST"));
        setAuthenticationManager(authManager);
        this.mapper = new ObjectMapper();
        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        UserResource userResource = mapper.readValue(req.getInputStream(), UserResource.class);
        Authentication authenticate;
        try {
            authenticate = getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(userResource.getEmail(), userResource.getPassword(), Collections.emptyList()));
        } catch (AuthenticationException e) {
            throw e;
        }
        return authenticate;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setContentType("application/json");
        response.setStatus(401);

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("statusInfo", "Unauthorized");
        responseObj.add("data", null);
        responseObj.addProperty("success", false);
        if ("Bad credentials".equals(failed.getMessage())) {
            responseObj.addProperty("errorMessage", "Invalid email or password");
        } else {
            responseObj.addProperty("errorMessage", failed.getMessage());
        }

        if ("Email address verification failed".equals(failed.getMessage())) {
            response.setStatus(409);
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        ServletInputStream inputStream = request.getInputStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);
        } catch (Exception e) { /*report an error*/ }
        PrintWriter writer = response.getWriter();
        writer.write(responseObj.toString());
        writer.close();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
                                            Authentication auth) throws IOException {
        String userUUID = ((UserCustomDetail) auth.getPrincipal()).getUuid();
        String accessToken = JwtGenerator.generateAccessJWT(auth.getName(), userUUID, auth.getAuthorities(), jwtAuthenticationConfig.getAccessTokenExpiration(), jwtAuthenticationConfig.getSecret());
        String refreshToken = JwtGenerator.generateRefreshToken(auth.getName(), userUUID, auth.getAuthorities(), jwtAuthenticationConfig.getRefreshTokenExpiration(), jwtAuthenticationConfig.getSecret());


        rsp.setContentType("application/json");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("access_token", accessToken);
        jsonObject.addProperty("refresh_token", refreshToken);

        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("statusInfo", "OK");
        responseObj.add("data", jsonObject);
        responseObj.addProperty("success", true);
        responseObj.add("errorMessage", null);

        PrintWriter writer = rsp.getWriter();
        writer.write(responseObj.toString());
        writer.close();
    }
}
