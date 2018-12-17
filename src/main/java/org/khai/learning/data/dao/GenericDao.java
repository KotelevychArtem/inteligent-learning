package org.khai.learning.data.dao;

public interface GenericDao<T> {
    Integer insert(T entry);

    T get(int id);

    void remove(int id);
}
