package com.eduscape.school;

import com.eduscape.keystone.KeystoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            names.add(s.getName());
        }

        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping(path="/find")
    public @ResponseBody ResponseEntity<School> find(String name) {
        Optional<School> school = schoolRepository.findSchoolByName(name);
        return school.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
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

        return new ResponseEntity<>(getData(school), HttpStatus.OK);
    }

    private SchoolDataNormalized getData(School school) {
        return new SchoolDataNormalized(
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
    }

    @GetMapping(path="/top25")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getTop25(String orderByClause) {
        List<School> schools = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schools.add(s);
        }
        schools.sort(Comparator.comparing(School::getName));

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (int i = 0; i < Math.min(schools.size(), 25); i++) {
            schoolData.add(getData(schools.get(i)));
        }

        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }

    @GetMapping(path="/bottom25")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getBottom25(String orderByClause) {
        List<School> schools = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schools.add(s);
        }
        schools.sort(Comparator.comparing(School::getName));

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (int i = schools.size()-1; i >= Math.max(schools.size() - 25, 0); i--) {
            schoolData.add(getData(schools.get(i)));
        }

        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }
}
