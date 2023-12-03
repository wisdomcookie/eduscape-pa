package com.eduscape.school;

import com.eduscape.query_objects.RateWrapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchoolDataRepository extends CrudRepository<SchoolData, Integer> {

    @Query(nativeQuery = true, value = "SELECT name, school_data.* " +
            "FROM (SELECT *FROM school WHERE name=?1) s NATURAL JOIN school_data " +
            "ORDER BY year")
    Optional<Iterable<Object>> findSchoolDataByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT name, school_data.* " +
            "FROM (SELECT *FROM school WHERE name=?1) s NATURAL JOIN school_data " +
            "WHERE year=?2")
    Optional<Iterable<Object>> findSchoolDataByName(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, name, AVG(dropout_count/total_enrollment) rate, s.dropout_percentile percent_rank " +
            "FROM school s JOIN school_data sd on s.school_number = sd.school_number " +
            "WHERE dropout_count>=0 AND name=?1")
    Optional<RateWrapper> getDropoutRate(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, name, AVG(dropout_count/total_enrollment) rate, sd.dropout_percentile percent_rank " +
            "FROM school s JOIN school_data sd on s.school_number = sd.school_number " +
            "WHERE dropout_count>=0 AND name=?1 AND year=?2")
    Optional<RateWrapper> getDropoutRate(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, name, AVG(low_income_enrollment/total_enrollment) rate, s.low_income_percentile percent_rank " +
            "FROM school s JOIN school_data sd on s.school_number = sd.school_number " +
            "WHERE low_income_enrollment>0 AND total_enrollment>=0 AND name=?1")
    Optional<RateWrapper> getPercentLowIncome(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, name, AVG(low_income_enrollment/total_enrollment) rate, sd.low_income_percentile percent_rank " +
            "FROM school s JOIN school_data sd on s.school_number = sd.school_number " +
            "WHERE low_income_enrollment>0 AND total_enrollment>=0 AND name=?1 AND year=?2")
    Optional<RateWrapper> getPercentLowIncome(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, name, AVG(college_bound/graduate_count) rate, s.college_bound_percentile percent_rank " +
            "FROM school s JOIN school_data sd on s.school_number = sd.school_number " +
            "WHERE college_bound>0 AND graduate_count>0 AND name=?1")
    Optional<RateWrapper> getCollegeBound(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, name, AVG(college_bound/graduate_count) rate, sd.college_bound_percentile percent_rank " +
            "FROM school s JOIN school_data sd on s.school_number = sd.school_number " +
            "WHERE college_bound>0 AND graduate_count>0 AND name=?1 AND year=?2")
    Optional<RateWrapper> getCollegeBound(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(expenditures/enrollment) rate, d.spending_per_student_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE expenditures>0 AND enrollment>0 AND s.name=?1")
    Optional<RateWrapper> getSpendingPerStudent(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(expenditures/enrollment) rate, dd.spending_per_student_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE expenditures>0 AND enrollment>0 AND s.name=?1 AND year=?2")
    Optional<RateWrapper> getSpendingPerStudent(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(enrollment/professional_personnel) rate, d.student_to_faculty_ratio_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE professional_personnel>0 AND enrollment>0 AND s.name=?1")
    Optional<RateWrapper> getFacultyToStudentRatio(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(enrollment/professional_personnel) rate, dd.student_to_faculty_ratio_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE professional_personnel>0 AND enrollment>0 AND s.name=?1 AND year=?2")
    Optional<RateWrapper> getFacultyToStudentRatio(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(average_experience) rate, d.average_degree_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE average_experience>0 AND s.name=?1")
    Optional<RateWrapper> getTeacherExperience(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(average_experience) rate, dd.average_degree_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE average_experience>0 AND s.name=?1 AND year=?2")
    Optional<RateWrapper> getTeacherExperience(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(average_degree) rate, d.average_experience_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE average_degree>0 AND s.name=?1")
    Optional<RateWrapper> getTeacherDegreeLevel(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(average_degree) rate, dd.average_experience_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE average_degree>0 AND s.name=?1 AND year=?2")
    Optional<RateWrapper> getTeacherDegreeLevel(@Param("name") String name, @Param("year") Integer year);


    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(average_salary) rate, d.average_salary_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE average_salary>0 AND s.name=?1")
    Optional<RateWrapper> getAverageTeacherSalary(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT s.school_number, s.name, AVG(average_salary) rate, dd.average_salary_percentile percent_rank " +
            "FROM school s JOIN district d ON s.aun = d.aun JOIN district_data dd ON d.aun = dd.aun " +
            "WHERE average_salary>0 AND s.name=?1 AND year=?2")
    Optional<RateWrapper> getAverageTeacherSalary(@Param("name") String name, @Param("year") Integer year);
}