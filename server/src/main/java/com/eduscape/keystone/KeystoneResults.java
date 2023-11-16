package com.eduscape.keystone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.NoArgsConstructor;

@Entity(name = "keystone_results")
@IdClass(KeystoneResultsKey.class)
@NoArgsConstructor
public class KeystoneResults {

    @Id
    @Column(name = "year")
    private short year;

    @Id
    @Column(name = "subject", columnDefinition = "varchar(16)")
    private String subject;

    @Id
    @Column(name = "grp", columnDefinition = "varchar(32)")
    private String group;

    @Column(name = "total")
    private int total;

    @Column(name = "percent_advanced")
    private float percentAdvanced;

    @Column(name = "percent_proficient")
    private float percentProficient;

    @Column(name = "percent_basic")
    private float percentBasic;

    @Column(name = "percent_below_basic")
    private float percentBelowBasic;

    public KeystoneResults(short year, String subject, String group, int total, float percentAdvanced, float percentProficient, float percentBasic, float percentBelowBasic) {
        this.year = year;
        this.subject = subject;
        this.group = group;
        this.total = total;
        this.percentAdvanced = percentAdvanced;
        this.percentProficient = percentProficient;
        this.percentBasic = percentBasic;
        this.percentBelowBasic = percentBelowBasic;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getPercentAdvanced() {
        return percentAdvanced;
    }

    public void setPercentAdvanced(float percentAdvanced) {
        this.percentAdvanced = percentAdvanced;
    }

    public float getPercentProficient() {
        return percentProficient;
    }

    public void setPercentProficient(float percentProficient) {
        this.percentProficient = percentProficient;
    }

    public float getPercentBasic() {
        return percentBasic;
    }

    public void setPercentBasic(float percentBasic) {
        this.percentBasic = percentBasic;
    }

    public float getPercentBelowBasic() {
        return percentBelowBasic;
    }

    public void setPercentBelowBasic(float percentBelowBasic) {
        this.percentBelowBasic = percentBelowBasic;
    }
}
