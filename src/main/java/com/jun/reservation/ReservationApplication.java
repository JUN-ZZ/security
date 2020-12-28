package com.jun.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);

    }

}
