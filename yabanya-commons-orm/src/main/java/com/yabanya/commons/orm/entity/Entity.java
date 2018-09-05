package com.yabanya.commons.orm.entity;

import java.io.Serializable;

/**
 * All relational database entities must implement this interface,
 * which represent the minimum requirement.
 */
public interface Entity extends Serializable, Cloneable, Comparable<Entity> {

    /**
     * Get entity identifier
     *
     * @return Id for the entity
     */
    Long getId();

    /**
     * Set entity identifier
     *
     * @param id of the entity to set.
     */
    void setId(final Long id);

    /**
     * Get entity unique identifier
     *
     * @return UID of the entity.
     */
    String getUID();

    /**
     * Set entity unique identifier
     *
     * @param uid to set to the entity
     */
    void setUID(final String uid);
    
    /**
     * Get the status of the entity
     *
     * @return RecordStatus as the entity status
     */
    RecordStatus getRecordStatus();

    /**
     * Set the status of the entity
     *
     * @param recordStatus the status to set to the entity
     */
    void setRecordStatus(final RecordStatus recordStatus);
}
