package cz.vance.movie_rover_web.model;

import java.time.LocalDateTime;

public record Signup(String username,
                     String email,
                     String password,
                     LocalDateTime timestamp) {}