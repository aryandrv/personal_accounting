package model.repository.impl;

import java.util.List;

public interface Repository<T> {
    T save(T t) throws Exception;
    T edit(T t) throws Exception;
    T remove(int id) throws Exception;
    List<T> findAll() throws Exception;
    T findById(int id) throws Exception;
}
