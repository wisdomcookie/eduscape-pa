package com.eduscape.school;

import com.eduscape.keystone.KeystoneRepository;
import com.eduscape.query_objects.RateWrapper;
import com.eduscape.query_objects.SchoolDataNormalized;
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
                teacherExperience.map(RateWrapper::getPercent_Rank).orElse(-1.0)
        );
    }

    private SchoolDataNormalized getData(String name, Integer year) {
        Optional<RateWrapper> dropoutRateData = schoolDataRepository.getDropoutRate(name, year);
        Optional<RateWrapper> lowIncomeData = schoolDataRepository.getPercentLowIncome(name, year);
        Optional<RateWrapper> collegeBoundData = schoolDataRepository.getCollegeBound(name, year);
        Optional<RateWrapper> spendingPerStudentData = schoolDataRepository.getSpendingPerStudent(name, year);
        Optional<RateWrapper> studentFacultyRatioData = schoolDataRepository.getFacultyToStudentRatio(name, year);
        Optional<RateWrapper> teacherDegreeLevel = schoolDataRepository.getTeacherDegreeLevel(name, year);
        Optional<RateWrapper> teacherExperience = schoolDataRepository.getTeacherExperience(name, year);

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
                teacherExperience.map(RateWrapper::getPercent_Rank).orElse(-1.0)
        );
    }

    @GetMapping(path="/top25")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getTop25(Integer year, String orderByClause) {
        return getTopSchools(25, year, orderByClause);
    }

    @GetMapping(path="/bottom25")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getBottom25(Integer year, String orderByClause) {
        return getBottomSchools(25, year, orderByClause);
    }

    /**
     * Gets the top n schools based on the provided ordering scheme
     * @param n The number of schools to get
     * @param year The year of data to get (leave blank to get all years)
     * @param orderByClause The ordering of the ORDER BY clause
     * @return The top n schools ordered
     */
    @GetMapping(path="/top")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getTopSchools(int n, Integer year, String orderByClause) {
        List<School> schools = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schools.add(s);
        }
        schools.sort(Comparator.comparing(School::getName));

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (int i = 0; i < Math.min(schools.size(), n); i++) {
            schoolData.add(year != null ? getData(schools.get(i).getName(), year) : getData(schools.get(i).getName()));
        }

        return new ResponseEntity<>(schoolData, HttpStatus.OK);
    }

    /**
     * Gets the bottom n schools based on the provided ordering scheme
     * @param n The number of schools to get
     * @param year The year of data to get (leave blank to get all years)
     * @param orderByClause The ordering of the ORDER BY clause
     * @return The bottom n schools ordered
     */
    @GetMapping(path="/bottom")
    public @ResponseBody ResponseEntity<Iterable<SchoolDataNormalized>> getBottomSchools(int n, Integer year, String orderByClause) {
        List<School> schools = new ArrayList<>();
        for (School s : schoolRepository.findAll()) {
            schools.add(s);
        }
        schools.sort(Comparator.comparing(School::getName));

        List<SchoolDataNormalized> schoolData = new ArrayList<>();
        for (int i = schools.size()-1; i >= Math.max(schools.size() - n, 0); i--) {
            schoolData.add(year != null ? getData(schools.get(i).getName(), year) : getData(schools.get(i).getName()));
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
}
