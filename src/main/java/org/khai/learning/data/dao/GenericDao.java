package org.khai.learning.data.dao;

public interface GenericDao<T> {
    int add(T entry);

    T get(int id);

    void remove(int id);
}
