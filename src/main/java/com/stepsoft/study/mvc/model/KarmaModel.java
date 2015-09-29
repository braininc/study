package com.stepsoft.study.mvc.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Eugene Stepanenkov
 */
public class KarmaModel {

    private Long id;
    private Long sinnerId;
    private Integer drunkBottles;
    private Integer maliciousLevel;
    private Boolean seenBlasphemy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSinnerId() {
        return sinnerId;
    }

    public void setSinnerId(Long sinnerId) {
        this.sinnerId = sinnerId;
    }

    public Integer getDrunkBottles() {
        return drunkBottles;
    }

    public void setDrunkBottles(Integer drunkBottles) {
        this.drunkBottles = drunkBottles;
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

        KarmaModel that = (KarmaModel) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(sinnerId, that.sinnerId)
                .append(drunkBottles, that.drunkBottles)
                .append(maliciousLevel, that.maliciousLevel)
                .append(seenBlasphemy, that.seenBlasphemy)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(sinnerId)
                .append(drunkBottles)
                .append(maliciousLevel)
                .append(seenBlasphemy)
                .toHashCode();
    }
}
