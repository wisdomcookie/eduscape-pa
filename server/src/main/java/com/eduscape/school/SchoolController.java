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
        return getTopSchools(25, orderByClause);
    }

    @GetMapping(path="/bottom25")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getBottom25(String orderByClause) {
        return getBottomSchools(25, orderByClause);
    }

    /**
     * Gets the top n schools based on the provided ordering scheme
     * @param n The number of schools to get
     * @param orderByClause The ordering of the ORDER BY clause
     * @return The top n schools ordered
     */
    @GetMapping(path="/top")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getTopSchools(int n, String orderByClause) {
        List<School> schools = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schools.add(s);
        }
        schools.sort(Comparator.comparing(School::getName));

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (int i = 0; i < Math.min(schools.size(), n); i++) {
            schoolData.add(getData(schools.get(i)));
        }

        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }

    /**
     * Gets the bottom n schools based on the provided ordering scheme
     * @param n The number of schools to get
     * @param orderByClause The ordering of the ORDER BY clause
     * @return The bottom n schools ordered
     */
    @GetMapping(path="/bottom")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getBottomSchools(int n, String orderByClause) {
        List<School> schools = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schools.add(s);
        }
        schools.sort(Comparator.comparing(School::getName));

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (int i = schools.size()-1; i >= Math.max(schools.size() - n, 0); i--) {
            schoolData.add(getData(schools.get(i)));
        }

        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }

    /**
     * Gets the bottom n schools based on the provided ordering scheme
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The bottom n schools ordered
     */
    @GetMapping(path="/dataRaw")
    public @ResponseBody ResponseEntity<Iterable<Object>> getRawSchoolData(String name, Integer year) {
        if (year == null) {
            Optional<Iterable<Object>> data = schoolDataRepository.findSchoolDataByName(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<Iterable<Object>> data = schoolDataRepository.findSchoolDataByName(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's graduation rate and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The graduation rate and its percentile
     */
    @GetMapping(path="/graduationRate")
    public @ResponseBody ResponseEntity<Object> getSchoolGraduationRate(String name, Integer year) {
        if (year == null) {
            Optional<Object> data = schoolDataRepository.getGraduationRate(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<Object> data = schoolDataRepository.getGraduationRate(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }
}
