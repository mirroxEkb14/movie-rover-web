package cz.vance.movie_rover_web.controller;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movie_rover_web.model.Signup;
import cz.vance.movie_rover_web.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
//</editor-fold>

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/submitSignup")
    public String submitSignup(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        final Signup signup = new Signup(username, email, password, LocalDateTime.now());
        signupService.saveSignup(signup);

        return "redirect:/?signedUp=true";
    }
}
