package com.eduscape.school;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchoolRepository extends CrudRepository<School, Integer> {

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM school " +
            "WHERE name=?1")
    Optional<School> findSchoolByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM Student " +
            "WHERE")
    float findSchoolGraduationRate(@Param("name") String name);
}
