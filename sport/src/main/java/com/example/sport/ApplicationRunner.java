package com.example.sport;

import com.example.sport.entities.Coach;
import com.example.sport.entities.enums.Sport;
import com.example.sport.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public void run(String... args) throws Exception {
        /*Coach coach = coachRepository.findById(2);

        System.out.println();*/
    }
}
