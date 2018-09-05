package com.yabanya.commons.orm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity implements Entity {

    private static final long serialVersionUID = 1987123924932012980L;

    @Id
    @GeneratedValue(generator = "optimized-sequence")
    @GenericGenerator(
            name = "optimized-sequence",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String uid;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if ( !(obj instanceof Entity) ) {
            return false;
        }

        if ( this.getClass().equals(obj.getClass()) ) {

            final Entity entity = (Entity) obj;

            return ( Objects.equals(this.getId(), entity.getId()) && Objects.equals(this.getUID(), entity.getUID()) ) ||
                    ( this.getId().equals(entity.getId()) && this.getUID().equals(entity.getUID() ) );
        }

        return false;
    }

    @Override
    public int hashCode() {

        int result = this.getUID().hashCode();
        result = this.getId() != null ? (this.getId().hashCode() + result + 31) : 31;

        return result;
    }

    @Override
    public int compareTo(Entity o) {
        return this.getUID().compareTo(o.getUID());
    }

    protected AbstractEntity() { this.uid = UUID.randomUUID().toString(); }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public void setUID(final String uid) {
        this.uid = uid;
    }

    @Override
    public String getUID() {
        return uid;
    }
}
