package com.stepsoft.study.mvc.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Set;

/**
 * @author Eugene Stepanenkov
 */
public class SinnerModel implements RestModel {

    private Long id;
    private String userName;
    private Set<KarmaModel> karmas;

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<KarmaModel> getKarmas() {
        return karmas;
    }

    public void setKarmas(Set<KarmaModel> karmas) {
        this.karmas = karmas;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SinnerModel that = (SinnerModel) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(userName, that.userName)
                .append(karmas, that.karmas)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(userName)
                .append(karmas)
                .toHashCode();
    }
}
