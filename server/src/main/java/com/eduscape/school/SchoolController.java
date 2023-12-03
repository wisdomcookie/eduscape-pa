package com.eduscape.school;

import com.eduscape.keystone.KeystoneRepository;
import com.eduscape.query_objects.OverallScoreWrapper;
import com.eduscape.query_objects.RateWrapper;
import com.eduscape.query_objects.SchoolDataNormalized;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/schools")
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolDataRepository schoolDataRepository;

    @Autowired
    private KeystoneRepository keystoneRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    /**
     * Gets all "rate" data for a school. This will call multiple individual queries.
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return All 5 rates and their percentiles
     */
    @GetMapping(path="/rates")
    public @ResponseBody ResponseEntity<SchoolDataNormalized> getRates(String name, Integer year) {
        return new ResponseEntity<>(year != null ? getData(name, year) : getData(name), HttpStatus.OK);
    }

    private SchoolDataNormalized getData(String name) {
        Optional<RateWrapper> dropoutRateData = schoolDataRepository.getDropoutRate(name);
        Optional<RateWrapper> lowIncomeData = schoolDataRepository.getPercentLowIncome(name);
        Optional<RateWrapper> collegeBoundData = schoolDataRepository.getCollegeBound(name);
        Optional<RateWrapper> spendingPerStudentData = schoolDataRepository.getSpendingPerStudent(name);
        Optional<RateWrapper> studentFacultyRatioData = schoolDataRepository.getFacultyToStudentRatio(name);
        Optional<RateWrapper> teacherDegreeLevel = schoolDataRepository.getTeacherDegreeLevel(name);
        Optional<RateWrapper> teacherExperience = schoolDataRepository.getTeacherExperience(name);
        Optional<RateWrapper> teacherSalary = schoolDataRepository.getAverageTeacherSalary(name);

        return new SchoolDataNormalized(
                name,
                dropoutRateData.map(RateWrapper::getRate).orElse(-1.0),
                dropoutRateData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                lowIncomeData.map(RateWrapper::getRate).orElse(-1.0),
                lowIncomeData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                collegeBoundData.map(RateWrapper::getRate).orElse(-1.0),
                collegeBoundData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                spendingPerStudentData.map(RateWrapper::getRate).orElse(-1.0),
                spendingPerStudentData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                studentFacultyRatioData.map(RateWrapper::getRate).orElse(-1.0),
                studentFacultyRatioData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                teacherDegreeLevel.map(RateWrapper::getRate).orElse(-1.0),
                teacherDegreeLevel.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                teacherExperience.map(RateWrapper::getRate).orElse(-1.0),
                teacherExperience.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                teacherSalary.map(RateWrapper::getRate).orElse(-1.0),
                teacherSalary.map(RateWrapper::getPercent_Rank).orElse(-1.0));
    }

    private SchoolDataNormalized getData(String name, Integer year) {
        Optional<RateWrapper> dropoutRateData = schoolDataRepository.getDropoutRate(name, year);
        Optional<RateWrapper> lowIncomeData = schoolDataRepository.getPercentLowIncome(name, year);
        Optional<RateWrapper> collegeBoundData = schoolDataRepository.getCollegeBound(name, year);
        Optional<RateWrapper> spendingPerStudentData = schoolDataRepository.getSpendingPerStudent(name, year);
        Optional<RateWrapper> studentFacultyRatioData = schoolDataRepository.getFacultyToStudentRatio(name, year);
        Optional<RateWrapper> teacherDegreeLevel = schoolDataRepository.getTeacherDegreeLevel(name, year);
        Optional<RateWrapper> teacherExperience = schoolDataRepository.getTeacherExperience(name, year);
        Optional<RateWrapper> teacherSalary = schoolDataRepository.getAverageTeacherSalary(name, year);

        return new SchoolDataNormalized(
                name,
                dropoutRateData.map(RateWrapper::getRate).orElse(-1.0),
                dropoutRateData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                lowIncomeData.map(RateWrapper::getRate).orElse(-1.0),
                lowIncomeData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                collegeBoundData.map(RateWrapper::getRate).orElse(-1.0),
                collegeBoundData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                spendingPerStudentData.map(RateWrapper::getRate).orElse(-1.0),
                spendingPerStudentData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                studentFacultyRatioData.map(RateWrapper::getRate).orElse(-1.0),
                studentFacultyRatioData.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                teacherDegreeLevel.map(RateWrapper::getRate).orElse(-1.0),
                teacherDegreeLevel.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                teacherExperience.map(RateWrapper::getRate).orElse(-1.0),
                teacherExperience.map(RateWrapper::getPercent_Rank).orElse(-1.0),
                teacherSalary.map(RateWrapper::getRate).orElse(-1.0),
                teacherSalary.map(RateWrapper::getPercent_Rank).orElse(-1.0));
    }

    /**
     * Gets n schools based on the provided ordering scheme
     * @param n The number of schools to get
     * @param order Sort by ascending or descending
     * @param type The percentile value to sort by
     * @param year The year of data to get (leave blank to get all years)
     * @return The top n schools ordered
     */
    @GetMapping(path="/ranking")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getTopSchools(Integer n, String order, String type, Integer year) {
        if (n == null || order == null || type == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Iterable<String> names = null;

        if (order.equalsIgnoreCase("ASC")) {
            switch (type) {
                case "dropoutRate" -> {
                    names = schoolDataRepository.getT1Top(n);
                }
                case "percentLowIncome" -> {
                    names = schoolDataRepository.getT2Top(n);
                }
                case "collegeBound" -> {
                    names = schoolDataRepository.getT3Top(n);
                }
                case "spendingPerStudent" -> {
                    names = schoolDataRepository.getT4Top(n);
                }
                case "facultyToStudentRatio" -> {
                    names = schoolDataRepository.getT5Top(n);
                }
                case "avgTeacherExperience" -> {
                    names = schoolDataRepository.getT6Top(n);
                }
                case "avgTeacherDegreeLevel" -> {
                    names = schoolDataRepository.getT7Top(n);
                }
                case "avgTeacherSalary" -> {
                    names = schoolDataRepository.getT8Top(n);
                }
            }
        }
        else if (order.equalsIgnoreCase("DESC")) {
            switch (type) {
                case "dropoutRate" -> {
                    names = schoolDataRepository.getT1Bottom(n);
                }
                case "percentLowIncome" -> {
                    names = schoolDataRepository.getT2Bottom(n);
                }
                case "collegeBound" -> {
                    names = schoolDataRepository.getT3Bottom(n);
                }
                case "spendingPerStudent" -> {
                    names = schoolDataRepository.getT4Bottom(n);
                }
                case "facultyToStudentRatio" -> {
                    names = schoolDataRepository.getT5Bottom(n);
                }
                case "avgTeacherExperience" -> {
                    names = schoolDataRepository.getT6Bottom(n);
                }
                case "avgTeacherDegreeLevel" -> {
                    names = schoolDataRepository.getT7Bottom(n);
                }
                case "avgTeacherSalary" -> {
                    names = schoolDataRepository.getT8Bottom(n);
                }
            }
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (names == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (String name : names) {
            schoolData.add(year != null ? getData(name, year) : getData(name));
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
     * Calculates the school's dropout rate and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The dropout rate and its percentile
     */
    @GetMapping(path="/dropoutRate")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolDropoutRate(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getDropoutRate(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getDropoutRate(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's percent of low income students and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The percent of low income students and its percentile
     */
    @GetMapping(path="/percentLowIncome")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolPercentLowIncome(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getPercentLowIncome(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getPercentLowIncome(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's percent of graduates going to college and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The percent of graduates going to college and its percentile
     */
    @GetMapping(path="/collegeBound")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolCollegeBound(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getCollegeBound(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getCollegeBound(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's (district) spending per student and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The spending per student rate and its percentile
     */
    @GetMapping(path="/spendingPerStudent")
    public @ResponseBody ResponseEntity<RateWrapper> getDistrictSpendingPerStudent(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getSpendingPerStudent(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getSpendingPerStudent(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's (district) student to faculty ratio and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The student to faculty ratio rate and its percentile
     */
    @GetMapping(path="/studentFacultyRatio")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolStudentToFacultyRatio(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getFacultyToStudentRatio(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getFacultyToStudentRatio(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's (district) teacher experience in years and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The teacher experience in years rate and its percentile
     */
    @GetMapping(path="/teacherDegreeLevel")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolTeacherDegree(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getTeacherDegreeLevel(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getTeacherDegreeLevel(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's (district) teacher education level and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The teacher education level rate and its percentile
     */
    @GetMapping(path="/teacherExperience")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolTeacherExperience(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getTeacherExperience(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getTeacherExperience(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's (district) average teacher salary and its percentile
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The average teacher salary and its percentile
     */
    @GetMapping(path="/teacherSalary")
    public @ResponseBody ResponseEntity<RateWrapper> getSchoolTeacherSalary(String name, Integer year) {
        if (year == null) {
            Optional<RateWrapper> data = schoolDataRepository.getAverageTeacherSalary(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<RateWrapper> data = schoolDataRepository.getAverageTeacherSalary(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }

    /**
     * Calculates the school's overall score based on Keystone data
     * @param name The name of the school
     * @param year The year of data to get (leave blank to get all years)
     * @return The overall score [0,100]
     */
    @GetMapping(path="/overallScore")
    public @ResponseBody ResponseEntity<OverallScoreWrapper> getSchoolOverallScore(String name, Integer year) {
        if (year == null) {
            Optional<OverallScoreWrapper> data = keystoneRepository.getSchoolOverallScore(name);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        else {
            Optional<OverallScoreWrapper> data = keystoneRepository.getSchoolOverallScore(name, year);
            return data.map(objects -> new ResponseEntity<>(objects, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
    }
}
