//package dev.oskarjohansson.projektarbetev2.configuration;
//
//import dev.oskarjohansson.projektarbetev2.model.MyUser;
//import dev.oskarjohansson.projektarbetev2.model.Roles;
//import dev.oskarjohansson.projektarbetev2.service.MyUserDetailService;
//import jakarta.annotation.PostConstruct;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreFilter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AddNewUsers {
//
//    private MyUserDetailService myUserDetailService;
//
//    public AddNewUsers(MyUserDetailService myUserDetailService) {
//        this.myUserDetailService = myUserDetailService;
//    }
//
//
//    @PostConstruct
//    void addTestUsers(){
//
//        MyUser admin = new MyUser(null,"ADMIN", "1234", new Roles("ADMIN"));
//        myUserDetailService.saveUser(admin);
//    }
//}
