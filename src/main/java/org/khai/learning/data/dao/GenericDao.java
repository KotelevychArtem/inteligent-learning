package org.khai.learning.data.dao;

import org.khai.learning.data.model.AbstractDto;

public interface GenericDao<T extends AbstractDto> {
    int add(T entry);

    T get(int id);

    void remove(int id);
}
