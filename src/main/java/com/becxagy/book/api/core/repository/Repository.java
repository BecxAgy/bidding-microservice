package com.becxagy.book.api.core.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Repository <E, I>{
    public void add(E obj);

    public void remove(E obj);

    public E get(I id);

    public boolean exists(E obj);

    public Page<E> all(Pageable pageable);
}
