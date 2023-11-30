package com.eduscape.school;

import jakarta.persistence.NamedQuery;
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
            "SELECT school_number, name, average_graduation_rate, percent_rank() " +
            "OVER ( ORDER BY average_graduation_rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, AVG(graduate_count/total_enrollment) average_graduation_rate " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE graduate_count>=0 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<Object> getGraduationRate(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM ( " +
            "SELECT school_number, name, average_graduation_rate, percent_rank() " +
            "OVER ( ORDER BY average_graduation_rate DESC ) " +
            "AS 'percent_rank' " +
            "FROM school NATURAL JOIN ( " +
            "SELECT school_number, name, year, AVG(graduate_count/total_enrollment) average_graduation_rate " +
            "FROM school NATURAL JOIN school_data " +
            "WHERE graduate_count>=0 AND year=?2 " +
            "GROUP BY school_number " +
            ") SD " +
            ") temp " +
            "WHERE name=?1")
    Optional<Object> getGraduationRate(@Param("name") String name, @Param("year") Integer year);

}