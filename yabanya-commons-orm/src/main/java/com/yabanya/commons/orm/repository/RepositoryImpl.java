package com.yabanya.commons.orm.repository;

import com.yabanya.commons.orm.entity.Entity;
import com.yabanya.commons.orm.entity.RecordStatus;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

public class RepositoryImpl<T extends Entity> extends SimpleJpaRepository<T, Long> implements Repository<T> {

    public static final int BATCH_SIZE = 100;

    protected final EntityManager entityManager;

    public RepositoryImpl(final Class<T> domainClass, final EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    public RepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void passivate(final Long id) {
        setRecordStatus(id, RecordStatus.PASSIVE);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void passivate(final T t) {
        setRecordStatus(t, RecordStatus.PASSIVE);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSoftly(final Long id) {
        setRecordStatus(id, RecordStatus.DELETED);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSoftly(final T t) {
        setRecordStatus(t, RecordStatus.DELETED);
    }

    @Override
    public void detach(final T t) {
        entityManager.detach(t);
    }

    @Override
    public void refresh(final T t) {
        entityManager.refresh(t);
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void batchSave(final Collection<T> ts) {

        int i = 0;

        for (T t : ts) {
            save(t);
            if (++i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T activate(Long id) {
        return setRecordStatus(id, RecordStatus.ACTIVE);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T activate(T t) {
        return setRecordStatus(t, RecordStatus.ACTIVE);
    }

    private T setRecordStatus(final Long id, final RecordStatus recordStatus) {
        final T t = Optional.of(getOne(id))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        return setRecordStatus(t, recordStatus);
    }

    private T setRecordStatus(final T t, final RecordStatus recordStatus) {

        if (t != null) {
            t.setRecordStatus(recordStatus);
            entityManager.persist(t);
        }

        return t;
    }
}
