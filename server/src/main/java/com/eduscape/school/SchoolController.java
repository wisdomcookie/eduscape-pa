package com.eduscape.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/schools")
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<String> add(
            @RequestParam int id,
            @RequestParam int aun,
            @RequestParam String name,
            @RequestParam double graduationRate,
            @RequestParam double dropoutRate,
            @RequestParam double spendingPerStudent,
            @RequestParam double facultyToStudentRatio,
            @RequestParam double averageTeacherEducationLevel,
            @RequestParam int percentile,
            @RequestParam int overall_rating) {

        School school = new School(
                id,
                aun,
                name,
                graduationRate,
                dropoutRate,
                spendingPerStudent,
                facultyToStudentRatio,
                averageTeacherEducationLevel,
                percentile,
                overall_rating
        );
        schoolRepository.save(school);

        return new ResponseEntity<>("Saved school\n", HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<School>> getAll() {
        return new ResponseEntity<>(schoolRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/allNames")
    public @ResponseBody ResponseEntity<Iterable<String>> getNames() {
        List<String> names = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            names.add(s.getName());
        }

        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}
