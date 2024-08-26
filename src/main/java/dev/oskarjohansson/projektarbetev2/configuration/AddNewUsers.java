package dev.oskarjohansson.projektarbetev2.configuration;

import dev.oskarjohansson.projektarbetev2.model.RegisterRequest;
import dev.oskarjohansson.projektarbetev2.model.RoleType;
import dev.oskarjohansson.projektarbetev2.model.Roles;
import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AddNewUsers {

    private MyUserDetailService myUserDetailService;

    public AddNewUsers(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @PostConstruct
    void addTestUsers() throws Exception {

        RegisterRequest admin = new RegisterRequest("ADMIN", "123456" , RoleType.ADMIN, true);
        myUserDetailService.saveUser(admin);
    }
}
