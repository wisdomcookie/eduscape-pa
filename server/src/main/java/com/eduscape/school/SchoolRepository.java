package com.eduscape.school;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchoolRepository extends CrudRepository<School, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Student ORDER BY age")
    Optional<School> findSchoolByName(@Param("name") String name);

}
