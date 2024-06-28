package cz.vance.movie_rover_web.service;

//<editor-fold default-state="collapsed" desc="Imports">
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import cz.vance.movie_rover_web.model.Feedback;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

@Service
public class FeedbackService {

    //<editor-fold default-state="collapsed" desc="Constants & Constructor">
    private final String FEEDBACK_FILE_PATH = "feedbacks.json";
    private final ObjectMapper objectMapper;

    public FeedbackService() {
        this.objectMapper = new ObjectMapper();
        configureObjectMapper();
    }
    //</editor-fold>

    /**
     * Saves the incoming {@link Feedback} entry to specified <b>json</b> file.
     */
    public void saveFeedback(Feedback feedback) {
        final List<Feedback> feedbackList = getAllFeedbacks();
        feedbackList.add(feedback);

        try {
            objectMapper.writeValue(new File(FEEDBACK_FILE_PATH), feedbackList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all the {@link Feedback} entries from the existing <b>json</b> file.
     * <br>
     * Is called each time a new feedback is submitted to make a "rewriting" of the existing entries.
     */
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList;
        try {
            feedbackList = objectMapper.readValue(new File(FEEDBACK_FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Feedback.class));
        } catch (IOException e) {
            feedbackList = new ArrayList<>();
        }
        return feedbackList;
    }

    //<editor-fold default-state="collapsed" desc="Private Methods">
    /**
     * Configures the {@link #objectMapper} to handle {@link LocalDateTime} and format <b>json</b> output.
     * <br>
     * Is called in the constructor.
     */
    private void configureObjectMapper() {
        final JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        this.objectMapper.registerModule(module);
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    //</editor-fold>
}
