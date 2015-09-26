package com.stepsoft.study.mvc.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Eugene Stepanenkov
 */
public class Karma {

    private Long id;
    private Integer drunkBottles;
    private Integer foulLanguageTimes;
    private Integer maliciousLevel;
    private Boolean seenBlasphemy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDrunkBottles() {
        return drunkBottles;
    }

    public void setDrunkBottles(Integer drunkBottles) {
        this.drunkBottles = drunkBottles;
    }

    public Integer getFoulLanguageTimes() {
        return foulLanguageTimes;
    }

    public void setFoulLanguageTimes(Integer foulLanguageTimes) {
        this.foulLanguageTimes = foulLanguageTimes;
    }

    public Integer getMaliciousLevel() {
        return maliciousLevel;
    }

    public void setMaliciousLevel(Integer maliciousLevel) {
        this.maliciousLevel = maliciousLevel;
    }

    public Boolean getSeenBlasphemy() {
        return seenBlasphemy;
    }

    public void setSeenBlasphemy(Boolean seenBlasphemy) {
        this.seenBlasphemy = seenBlasphemy;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Karma karma = (Karma) o;

        return new EqualsBuilder()
                .append(id, karma.id)
                .append(drunkBottles, karma.drunkBottles)
                .append(foulLanguageTimes, karma.foulLanguageTimes)
                .append(maliciousLevel, karma.maliciousLevel)
                .append(seenBlasphemy, karma.seenBlasphemy)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(drunkBottles)
                .append(foulLanguageTimes)
                .append(maliciousLevel)
                .append(seenBlasphemy)
                .toHashCode();
    }
}
