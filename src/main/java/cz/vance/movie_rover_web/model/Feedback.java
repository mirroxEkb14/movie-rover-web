package cz.vance.movie_rover_web.model;

import java.time.LocalDateTime;

/**
 *
 */
public record Feedback(String name,
                       String email,
                       String message,
                       LocalDateTime timestamp) {}
