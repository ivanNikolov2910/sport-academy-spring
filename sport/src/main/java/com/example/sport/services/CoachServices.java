package com.example.sport.services;

import com.example.sport.entities.Coach;
import com.example.sport.entities.enums.Sport;
import com.example.sport.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoachServices {

    @Autowired
    private CoachRepository coachRepository;

    /*POST*/
    public void addNewCoach(Coach coach) {
        this.coachRepository.saveAndFlush(coach);
    }

    /*GET*/
    public String searchCoaches(String search /*all || by sports*/) {
        List<Coach> coaches = this.coachRepository.findAll();
        StringBuilder builder = new StringBuilder();
        switch (search) {
            case "all":
                List<String> all = coaches.stream().map(CoachServices::toString).collect(Collectors.toList());
                for (String result : all) {
                    builder.append(result);
                }
                return builder.toString();
            case "tennis":
                return getResult(coaches, builder, Sport.TENNIS);
            case "basketball":
                return getResult(coaches, builder, Sport.BASKETBALL);
            case "football":
                return getResult(coaches, builder, Sport.FOOTBALL);
            case "swimming":
                return getResult(coaches, builder, Sport.SWIMMING);
            default:
                return null;
        }
    }

    /*DELETE*/
    public void deleteCoach(long index) {
        List<Coach> coaches = coachRepository.findAll();
        for (Coach oldCoach : coaches) {
            if (index == oldCoach.getId()){
                coachRepository.delete(oldCoach);
            }
        }

    }

    public void updateCoachSport(Coach coach){
        Coach oldCoach = coachRepository.getOne(coach.getId());

    }


    private String getResult(List<Coach> coaches, StringBuilder builder, Sport sport) {
        deleteData(builder);

        List<String> results = coaches.stream().filter(coach -> coach.getSport().equals(sport))
                .map(CoachServices::toString).collect(Collectors.toList());

        for (String result : results) {
            builder.append(result);
        }
        if (builder.length() == 0){
            return "Still no coaches";
        }
        return builder.toString();
    }

    private void deleteData(StringBuilder builder) {
        if (builder.length() > 0) {
            builder.delete(0, builder.length() - 1);
        } else {
            builder.delete(0, 0);
        }
    }

    private static String toString(Coach coach) {
        return String.format("Name: %s%nFamily: %s%nSport: %s%n%n", coach.getFirstName(), coach.getLastName(), coach.getSport().name());
    }

}
