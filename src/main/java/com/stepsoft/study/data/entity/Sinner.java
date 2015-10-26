package com.stepsoft.study.data.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SQLDelete;

import static com.stepsoft.study.data.utils.EntityConstants.IS_PROCESSED;
import static com.stepsoft.study.data.utils.EntityConstants.SINNER;
import static com.stepsoft.study.data.utils.EntityConstants.UPDATE_SINNER_SET_IS_PROCESSED_TRUE_WHERE_ID;
import static com.stepsoft.study.data.utils.EntityConstants.USER_NAME;
import static org.hibernate.annotations.ResultCheckStyle.COUNT;

/**
 * @author Eugene Stepanenkov
 */
@Entity
@SQLDelete(sql = UPDATE_SINNER_SET_IS_PROCESSED_TRUE_WHERE_ID, check = COUNT)
public class Sinner {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = SINNER)
    private Set<Karma> karmas;

    @Column(name = USER_NAME)
    private String userName;

    @Column(name = IS_PROCESSED)
    private Boolean isProcessed;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Set<Karma> getKarmas() {
        return karmas;
    }

    public void setKarmas(final Set<Karma> karmas) {
        this.karmas = karmas;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Boolean getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(final Boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final Sinner sinner = (Sinner) o;

        return new EqualsBuilder()
                .append(id, sinner.id)
                .append(karmas, sinner.karmas)
                .append(userName, sinner.userName)
                .append(isProcessed, sinner.isProcessed)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(karmas)
                .append(userName)
                .append(isProcessed)
                .toHashCode();
    }
}
