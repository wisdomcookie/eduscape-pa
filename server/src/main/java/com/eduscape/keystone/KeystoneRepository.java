package com.eduscape.keystone;

import com.eduscape.query_objects.OverallScoreWrapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KeystoneRepository extends CrudRepository<Keystone, Integer> {

    @Query(nativeQuery = true, value = "SELECT temp.school_number, name, AVG(overall_score) overall_score_average " +
            "FROM " +
            "( " +
            "SELECT school_number, s.year, s.subject, s.score me, a.score state, IF(s.score<a.score, (s.score-1)/(a.score-1)*50, (s.score-a.score)/(4-a.score)*50+50) overall_score " +
            "FROM " +
            "( " +
            "SELECT school_number, year, subject, (percent_below_basic * 1 + percent_basic * 2 + percent_proficient * 3 + percent_advanced * 4) / 100 score " +
            "FROM keystone " +
            "WHERE grp = \"All Students\" AND total > 10 " +
            ") s,  " +
            "( " +
            "SELECT year, subject, (percent_below_basic * 1 + percent_basic * 2 + percent_proficient * 3 + percent_advanced * 4) / 100 score " +
            "FROM keystone_results " +
            "WHERE grp = \"All Students\" " +
            ") a " +
            "WHERE s.year = a.year AND s.subject = a.subject " +
            ") temp JOIN school s ON s.school_number = temp.school_number " +
            "WHERE name=?1 " +
            "GROUP BY school_number")
    Optional<OverallScoreWrapper> getSchoolOverallScore(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT temp.school_number, name, AVG(overall_score) overall_score_average " +
            "FROM " +
            "( " +
            "SELECT school_number, s.year, s.subject, s.score me, a.score state, IF(s.score<a.score, (s.score-1)/(a.score-1)*50, (s.score-a.score)/(4-a.score)*50+50) overall_score " +
            "FROM " +
            "( " +
            "SELECT school_number, year, subject, (percent_below_basic * 1 + percent_basic * 2 + percent_proficient * 3 + percent_advanced * 4) / 100 score " +
            "FROM keystone " +
            "WHERE grp = \"All Students\" AND total > 10 " +
            ") s,  " +
            "( " +
            "SELECT year, subject, (percent_below_basic * 1 + percent_basic * 2 + percent_proficient * 3 + percent_advanced * 4) / 100 score " +
            "FROM keystone_results " +
            "WHERE grp = \"All Students\" " +
            ") a " +
            "WHERE s.year = a.year AND s.subject = a.subject AND s.year = ?2 " +
            ") temp JOIN school s ON s.school_number = temp.school_number " +
            "WHERE name=?1 " +
            "GROUP BY school_number")
    Optional<OverallScoreWrapper> getSchoolOverallScore(@Param("name") String name, @Param("year") Integer year);

}
