package lk.gedaratama.backendserver.controller;
import lk.gedaratama.backendserver.resource.DeliveryResource;
import lk.gedaratama.backendserver.response.MessageResponse;
import lk.gedaratama.backendserver.service.DeliveryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  @author Kavindya Sewwandi on 7/1/2020.
 */

@RestController
@CrossOrigin
public class DeliverySignupController {
    private final DeliveryDetailService userDetailsService;

    @Autowired
    public DeliverySignupController(DeliveryDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST,
            produces={"application/json"}, consumes={"application/x-www-form-urlencoded"})
    public ResponseEntity<?> saveUser( DeliveryResource user) {

        userDetailsService.userRegister(user);
       // return "OK";
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
