package com.yabanya.commons.orm.repository;

import com.yabanya.commons.orm.entity.AbstractAuditedEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "PERSON")
@Access(AccessType.FIELD)
public class Person extends AbstractAuditedEntity {

    private static final long serialVersionUID = -3740466299064029218L;

    @Column
    private String name;

    @Column
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }
}
