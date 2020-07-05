package lk.gedaratama.backendserver.controller;

import lk.gedaratama.backendserver.resource.UserResource;
import lk.gedaratama.backendserver.response.MessageResponse;
import lk.gedaratama.backendserver.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  @author Sashini Tharuka on 5/31/2020.
 */

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserResource user) {
        userDetailsService.userRegister(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}