package com.stepsoft.study.data.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static com.stepsoft.study.data.utils.EntityConstants.SINNER_ID;

/**
 * @author Eugene Stepanenkov
 */
@Entity
public class Karma {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = SINNER_ID, updatable = false, insertable = false)
    private Long sinnerId;

    @ManyToOne
    @JoinColumn(name = SINNER_ID)
    private Sinner sinner;

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

    public Sinner getSinner() {
        return sinner;
    }

    public void setSinner(Sinner sinner) {
        this.sinner = sinner;
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

        Karma karma = (Karma) o;

        return new EqualsBuilder()
                .append(id, karma.id)
                .append(sinnerId, karma.sinnerId)
                .append(drunkBottles, karma.drunkBottles)
                .append(maliciousLevel, karma.maliciousLevel)
                .append(seenBlasphemy, karma.seenBlasphemy)
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
