package com.stepsoft.study.data.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

import static com.stepsoft.study.data.utils.EntityConstants.SINNER;
import static javax.persistence.CascadeType.ALL;

/**
 * @author Eugene Stepanenkov
 */
@Entity
public class Sinner implements DbDto {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = SINNER, cascade = ALL)
    private Set<Karma> karma;

    private String userName;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Karma> getKarma() {
        return karma;
    }

    public void setKarma(Set<Karma> karma) {
        this.karma = karma;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Sinner sinner = (Sinner) o;

        return new EqualsBuilder()
                .append(id, sinner.id)
                .append(karma, sinner.karma)
                .append(userName, sinner.userName)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(karma)
                .append(userName)
                .toHashCode();
    }
}
