package com.eduscape.school;

import com.eduscape.keystone.KeystoneRepository;
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

    @Autowired
    private SchoolDataRepository schoolDataRepository;

    @Autowired
    private KeystoneRepository keystoneRepository;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<School>> getAll() {
        return new ResponseEntity<>(schoolRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/allNames")
    public @ResponseBody ResponseEntity<Iterable<String>> getNames() {
        List<String> names = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schoolRepository.findAll();
            names.add(s.getName());
        }

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping(path="/rates")
    public @ResponseBody ResponseEntity<SchoolDataNormalized> getRates(String name) {
        School school = null;
        for (School s : schoolRepository.findAll()) {
            if (s.getName().equals(name)) {
                school = s;
                break;
            }
        }
        if (school == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        SchoolDataNormalized schoolDataNormalized = new SchoolDataNormalized(
                school.getName(),
                Math.random(),
                0,
                Math.random(),
                0,
                Math.random(),
                0,
                Math.random(),
                0,
                Math.random(),
                0
        );

        return new ResponseEntity<>(schoolDataNormalized, HttpStatus.OK);
    }
}
