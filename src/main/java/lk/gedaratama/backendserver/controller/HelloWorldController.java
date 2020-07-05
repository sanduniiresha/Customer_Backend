package lk.gedaratama.backendserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author Sashini Tharuka on 5/31/2020.
 */

@RestController
public class HelloWorldController {

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

}

