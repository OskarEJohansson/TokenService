package dev.oskarjohansson.projektarbetev2.controller;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
public class HomeController {

    private final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MyUserDetailService userDetailService;

    @GetMapping("/")
    public String home(Principal principal) {

        if (principal != null) {
            LOG.info("Logging principal{}", principal.getName());
            return "Hello, " + principal.getName();
        } else {
            return "Hello, Guest";
        }
    }

    @GetMapping("/user")
    public String user(Principal principal){

        LOG.info("Running get/user");
        return "Hello, " + principal.getName();
    }

    @GetMapping("/admin")
    public String admin(Principal principal) {
        LOG.debug("DEBUG, Running get/admin, principal: {}", principal.getName());
        return "Hello, " + principal.getName();
    }

    @GetMapping("/users")
    public List<MyUser> getUsers(){

        try {
            return userDetailService.getUsers();
        }
        catch (IllegalArgumentException e){
            e.getStackTrace();

            return null;
        }
    }


}
