package br.net.silva.daniel.skipedlocked;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SkipedLockedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkipedLockedApplication.class, args);
    }

}
