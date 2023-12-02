package com.eduscape.query_objects;

import jakarta.persistence.Column;

public interface RateWrapper {
    Integer getSchool_Number();
    String getName();
    Double getRate();
    Double getPercent_Rank();
}
