package com.yabanya.commons.service;

import com.yabanya.commons.orm.entity.AbstractEntity;

import java.util.Optional;

public interface AbstractService<E extends AbstractEntity> {

    E create(final E t);

    E findById(final Long id);

    E update(final E t);

    void delete(final Long id);

    E activate(final Long id);

    void passivate(final Long id);

    void deleteSoftly(final Long id);
}
