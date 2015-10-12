package com.stepsoft.study.mvc.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Eugene Stepanenkov
 */
public class SinnerSummaryModel {

    private Long id;
    private Long sinnerId;
    private String sinnerUserName;
    private Long drunkBottles;
    private Integer maliciousLevel;
    private Integer seenBlasphemy;

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

    public String getSinnerUserName() {
        return sinnerUserName;
    }

    public void setSinnerUserName(String sinnerUserName) {
        this.sinnerUserName = sinnerUserName;
    }

    public Long getDrunkBottles() {
        return drunkBottles;
    }

    public void setDrunkBottles(Long drunkBottles) {
        this.drunkBottles = drunkBottles;
    }

    public Integer getMaliciousLevel() {
        return maliciousLevel;
    }

    public void setMaliciousLevel(Integer maliciousLevel) {
        this.maliciousLevel = maliciousLevel;
    }

    public Integer getSeenBlasphemy() {
        return seenBlasphemy;
    }

    public void setSeenBlasphemy(Integer seenBlasphemy) {
        this.seenBlasphemy = seenBlasphemy;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SinnerSummaryModel that = (SinnerSummaryModel) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(sinnerId, that.sinnerId)
                .append(sinnerUserName, that.sinnerUserName)
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
                .append(sinnerUserName)
                .append(drunkBottles)
                .append(maliciousLevel)
                .append(seenBlasphemy)
                .toHashCode();
    }
}
