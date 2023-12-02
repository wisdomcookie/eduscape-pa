package com.eduscape.school;

import com.eduscape.query_objects.RateWrapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchoolDataRepository extends CrudRepository<SchoolData, Integer> {

    @Query(nativeQuery = true, value = "SELECT name, school_data.* " +
            "FROM (SELECT * FROM school WHERE name=?1) s NATURAL JOIN school_data " +
            "ORDER BY year")
    Optional<Iterable<Object>> findSchoolDataByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT name, school_data.* " +
            "FROM (SELECT * FROM school WHERE name=?1) s NATURAL JOIN school_data " +
            "WHERE year=?2")
    Optional<Iterable<Object>> findSchoolDataByName(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_graduation_rate rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, AVG(graduate_count/total_enrollment) average_graduation_rate " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE graduate_count>=0 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getGraduationRate(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_graduation_rate rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, year, AVG(graduate_count/total_enrollment) average_graduation_rate " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE graduate_count>=0 AND year=?2 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getGraduationRate(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_dropout_rate rate, percent_rank() " +
            "OVER ( ORDER BY rate ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, AVG(dropout_count/total_enrollment) average_dropout_rate " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE dropout_count>=0 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getDropoutRate(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_dropout_rate rate, percent_rank() " +
            "OVER ( ORDER BY rate ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, year, AVG(graduate_count/total_enrollment) average_dropout_rate " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE dropout_count>=0 AND year=?2 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getDropoutRate(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, percent_low_income rate, percent_rank() " +
            "OVER ( ORDER BY rate ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, AVG(low_income_enrollment/total_enrollment) percent_low_income " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE low_income_enrollment>0 AND total_enrollment>=0 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getPercentLowIncome(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, percent_low_income rate, percent_rank() " +
            "OVER ( ORDER BY rate ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, year, AVG(low_income_enrollment/total_enrollment) percent_low_income " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE low_income_enrollment>0 AND total_enrollment>=0 AND year=?2 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getPercentLowIncome(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_spending rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, AVG(expenditures/enrollment) average_spending " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE expenditures>0 AND enrollment>0 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getSpendingPerStudent(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_spending rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, year, AVG(expenditures/enrollment) average_spending " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE expenditures>0 AND enrollment>0 AND year=?2 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getSpendingPerStudent(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_teacher_student_ratio rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, AVG(enrollment/professional_personnel) average_teacher_student_ratio " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE professional_personnel>0 AND enrollment>0 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getFacultyToStudentRatio(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_teacher_student_ratio rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, year, AVG(enrollment/professional_personnel) average_teacher_student_ratio " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE professional_personnel>0 AND enrollment>0 AND year=?2 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getFacultyToStudentRatio(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_teacher_experience rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, AVG(average_experience) average_teacher_experience " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE average_experience>0 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getTeacherExperience(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_teacher_experience rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, year, AVG(average_experience) average_teacher_experience " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE average_experience>0 AND year=?2 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getTeacherExperience(@Param("name") String name, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_teacher_education rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, AVG(average_degree) average_teacher_education " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE average_degree>0 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getTeacherDegreeLevel(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_teacher_education rate, percent_rank() " +
            "OVER ( ORDER BY rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT aun, year, AVG(average_degree) average_teacher_education " +
            "FROM district NATURAL JOIN district_data " +
            "WHERE average_degree>0 AND year=?2 " +
            "GROUP BY aun " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<RateWrapper> getTeacherDegreeLevel(@Param("name") String name, @Param("year") Integer year);

}