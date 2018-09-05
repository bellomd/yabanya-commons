package com.yabanya.commons.service.impl;

import com.yabanya.commons.orm.entity.AbstractEntity;
import com.yabanya.commons.orm.repository.Repository;
import com.yabanya.commons.service.AbstractService;
import com.yabanya.commons.utils.exception.EntityNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class AbstractServiceImpl<E extends AbstractEntity, R extends Repository<E>> implements AbstractService<E> {

    private final R repository;

    protected AbstractServiceImpl(final R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E create(final E t) {
        return repository.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public E findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E update(final E t) {
        return repository.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E activate(final Long id) {
        return repository.activate(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void passivate(final Long id) {
        repository.passivate(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSoftly(final Long id) {
        repository.deleteSoftly(id);
    }

    protected R getRepository() {
        return repository;
    }
}
