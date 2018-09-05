package com.yabanya.commons.orm.repository;

import com.yabanya.commons.orm.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface Repository<T extends Entity> extends JpaRepository<T, Long> {

    /**
     * Active entity with the given id
     *
     * @param id of the entity to active
     * @return T the activated entity.
     */
    T activate(final Long id);

    /**
     * Active the given entity
     *
     * @param t entity to active
     * @return T the activated entity.
     */
    T activate(final T t);

    /**
     * Passive entity with the given id
     *
     * @param id of the entity to passivate.
     */
    void passivate(final Long id);

    /**
     * Passive the given entity
     *
     * @param t  entity to passivate.
     */
    void passivate(final T t);

    /**
     * Delete entity with the given id
     *
     * @param id of the entity to delete.
     */
    void deleteSoftly(final Long id);


    /**
     * Delete the given entity
     *
     * @param t entity to delete.
     */
    void deleteSoftly(final T t);

    /**
     * Detach the given entity
     *
     * @param t entity to detach.
     */
    void detach(final T t);

    /**
     * Refresh the given entity
     *
     * @param t entity to refresh.
     */
    void refresh(final T t);

    /**
     * Clear entity manager
     */
    void clear();

    /**
     * Bulk saving of entities
     * @param ts entities to save
     */
    void batchSave(final Collection<T> ts);
}