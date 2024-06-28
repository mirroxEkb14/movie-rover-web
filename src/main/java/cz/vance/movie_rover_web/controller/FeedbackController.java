package cz.vance.movie_rover_web.controller;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movie_rover_web.model.Feedback;
import cz.vance.movie_rover_web.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
//</editor-fold>

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submitFeedback")
    @ResponseBody
    public String submitFeedback(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("message") String message) {

        final Feedback feedback = new Feedback(name, email, message, LocalDateTime.now());
        feedbackService.saveFeedback(feedback);

        return "success";
    }
}
