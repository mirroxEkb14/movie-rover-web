package cz.vance.movie_rover_web.service;

//<editor-fold default-state="collapsed" desc="Imports">
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import cz.vance.movie_rover_web.model.Signup;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

@Service
public class SignupService {

    //<editor-fold default-state="collapsed" desc="Constants & Constructor">
    private final String SIGNUP_FILE_PATH = "signups.json";
    private final ObjectMapper objectMapper;

    public SignupService() {
        this.objectMapper = new ObjectMapper();
        configureObjectMapper();
    }
    //</editor-fold>

    /**
     * Saves the incoming {@link Signup} entry to specified <b>json</b> file.
     */
    public void saveSignup(Signup signUpForm) {
        final List<Signup> signupList = getAllSignups();
        signupList.add(signUpForm);

        try {
            objectMapper.writeValue(new File(SIGNUP_FILE_PATH), signupList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all the {@link Signup} entries from the existing <b>json</b> file.
     * <br>
     * Is called each time a new signup entry is submitted to make a "rewriting" of the existing ones.
     */
    public List<Signup> getAllSignups() {
        List<Signup> signupsList;
        try {
            signupsList = objectMapper.readValue(new File(SIGNUP_FILE_PATH),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Signup.class));
        } catch (IOException e) {
            signupsList = new ArrayList<>();
        }
        return signupsList;
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
        objectMapper.registerModule(module);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    //</editor-fold>
}
