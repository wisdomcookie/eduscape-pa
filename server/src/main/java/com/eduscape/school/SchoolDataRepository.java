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

    Optional<Object> getGraduationRate(@Param("name") String name);

//    @Query(nativeQuery = true, value = "SELECT name, school_data.* " +
//            "FROM (SELECT * FROM school WHERE name=?1) s NATURAL JOIN school_data " +
//            "WHERE year=?2")
//    Optional<Object> getGraduationRate(@Param("name") String name, @Param("year") Integer year);

}