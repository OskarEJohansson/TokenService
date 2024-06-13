package dev.oskarjohansson.projektarbetev2.controller;

import dev.oskarjohansson.projektarbetev2.model.MyUser;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegisterController {


    private MyUserDetailService myUserDetailService;

    public RegisterController(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody MyUser user) {

        try {
            ResponseEntity response = myUserDetailService.saveUser(user);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
}
