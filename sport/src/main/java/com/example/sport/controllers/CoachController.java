package com.example.sport.controllers;

import com.example.sport.entities.Coach;
import com.example.sport.services.CoachServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoachController {

    @Autowired
    private CoachServices coachServices;

    @PostMapping("/addCoach")
    public String addNewCoach(@RequestBody Coach coach) {
        coachServices.addNewCoach(coach);
        return "New coach is added in database";
    }

    @GetMapping("/{search}")/*all || by sports*/
    public String searchCoach(@PathVariable String search) {
        return coachServices.searchCoaches(search);
    }

    @DeleteMapping("/deleteCoach/{index}")
    public String deleteCoach(@PathVariable int index) {
        coachServices.deleteCoach(index);
        return "successfully deleted sport";
    }

    /*@PutMapping("/updateCoach/sport")
    public void updateCoachSport(@RequestBody Coach coach){
        coachServices.updateCoachSport(coach);
    }*/

}
