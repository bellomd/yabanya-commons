package com.yabanya.commons.orm.entity;

import java.time.LocalDateTime;

/**
 * Entity that requires auditing must extends/implement this entity to
 * meet the minimum requirement.
 */
public interface AuditedEntity extends Entity {

    /**
     * Version information that is used for Optimistic locking
     *
     * @return version
     */
    Integer getVersion();

    /**
     * Information regarding entity creation. If entity is created by a signed in user
     * this area holds session token.
     *
     * @return entity created by information
     */
    String getCreatedBy();

    /**
     * If entity is updated this area hold information regarding who updated entity.
     * If entity is update by a signed in user this area holds session token.
     *
     * @return entity updated by information
     */
    String getLastModifiedBy();

    /**
     * Get entity creation date
     * @return ZoneDateTime as the creation date
     */
    LocalDateTime getCreatedDate();

    /**
     * Get entity update date
     * @return entity update date
     */
    LocalDateTime getLastModifiedDate();
}
