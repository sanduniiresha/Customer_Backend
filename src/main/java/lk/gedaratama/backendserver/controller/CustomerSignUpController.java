package lk.gedaratama.backendserver.controller;

import lk.gedaratama.backendserver.resource.CustomerResource;
import lk.gedaratama.backendserver.response.MessageResponse;
import lk.gedaratama.backendserver.service.CustomerDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CustomerSignUpController{
    private final CustomerDetailService userDetailsService;

    @Autowired
    public CustomerSignUpController(CustomerDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/customersignup", method = RequestMethod.POST,
            produces={"application/json"}, consumes={"application/x-www-form-urlencoded"})
    public ResponseEntity<?> saveUser( CustomerResource user) {
        userDetailsService.userRegister(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
