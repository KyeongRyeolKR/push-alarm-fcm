package com.example.pushmodule.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class FcmInitializer {

    private static final String FIREBASE_CONFIG_PATH = "push-module-1aadf-firebase-adminsdk-hsh3t-d21340ab27.json";

    @PostConstruct
    public void init() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream());

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(googleCredentials)
                    .build();

            FirebaseApp.initializeApp(options);
            log.info("FCM init success!");
        } catch (IOException e) {
            log.info("FCM error");
            log.error("FCM error message : " + e.getMessage());
        }
    }
}
