package com.pentagono.pentagono.service.Impl;

import com.pentagono.pentagono.repository.IGenericRepository;
import com.pentagono.pentagono.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl <T, ID> implements ICRUD<T,ID> {
    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T create(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }
    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
