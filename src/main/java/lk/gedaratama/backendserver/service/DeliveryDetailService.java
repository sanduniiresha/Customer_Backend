package lk.gedaratama.backendserver.service;
import lk.gedaratama.backendserver.config.JwtAuthenticationConfig;
import lk.gedaratama.backendserver.config.JwtGenerator;
import lk.gedaratama.backendserver.repository.DeliveryDao;
import lk.gedaratama.backendserver.model.DeliveryPersonDetail;
import lk.gedaratama.backendserver.jwt.JwtUserDetailService;
import lk.gedaratama.backendserver.resource.DeliveryResource;
import lk.gedaratama.backendserver.resource.UserResource;
import lk.gedaratama.backendserver.util.GedaratamaParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

/**
 *  @author Kavindya Sewwandi on 6/22/2020.
 */

@Service

public class DeliveryDetailService {
    private final DeliveryDao userDao;
    private final PasswordEncoder bcryptEncoder;
    private final JwtAuthenticationConfig jwtAuthenticationConfig;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailService jwtUserDetailService;

    @Autowired
    //c onsructor ekak hadala
    public DeliveryDetailService(DeliveryDao userDao, PasswordEncoder bcryptEncoder, JwtAuthenticationConfig jwtAuthenticationConfig, AuthenticationManager authenticationManager, JwtUserDetailService jwtUserDetailService) {
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    public DeliveryPersonDetail userRegister(DeliveryResource user) {
        //user entity ekak hadala tyenne, e kiyanne table ekata galapenna hadala tyenne
        DeliveryPersonDetail newUser = new DeliveryPersonDetail();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setFirstname(user.getFirstname());
       // newUser.setLastname(user.getLastname());
        newUser.setContact(user.getContact());
        newUser.setRole(GedaratamaParam.USER_NORMAL);
        newUser.setUuid(UUID.randomUUID().toString());
        return userDao.save(newUser);
    }

    private String createAccessToken(UserResource userResource) {

        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(GedaratamaParam.ROLE_PREFIX + userResource.getRole()));

        return JwtGenerator.generateAccessJWT(userResource.getUsername(), userResource.getUuid(),
                grantedAuthorityList, jwtAuthenticationConfig.getAccessTokenExpiration(), jwtAuthenticationConfig.getSecret());
    }

    private String createRefreshToken(UserResource userResource) {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(GedaratamaParam.ROLE_PREFIX + userResource.getRole()));
        return JwtGenerator.generateRefreshToken(userResource.getUsername(), userResource.getUuid(),
                grantedAuthorityList, jwtAuthenticationConfig.getRefreshTokenExpiration(), jwtAuthenticationConfig.getSecret());
    }

   /* public void userRegister(DeliveryResource user) {
    }*/

}
