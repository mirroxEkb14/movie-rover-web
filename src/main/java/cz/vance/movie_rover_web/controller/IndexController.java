package cz.vance.movie_rover_web.controller;

//<editor-fold default-state="collapsed" desc="Imports">
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//</editor-fold>

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/bot-abstract")
    public String botAbstract() { return "bot-abstract"; }

    @GetMapping("/telegram")
    public String telegram() { return "telegram"; }

    @GetMapping("/bot-updates")
    public String botUpdates() { return "bot-updates"; }

    @GetMapping("/bot-feedback")
    public String botFeedback() { return "bot-feedback"; }

    @GetMapping("/signup")
    public String signup() { return "signup"; }
}
