package com.zemoso.codezorro.usermanagement.helper;

import com.zemoso.codezorro.usermanagement.model.Model;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class StorageProvider<K extends Model> {

    private Map<Long,K> store = new HashMap<>();
    private AtomicLong pkey = new AtomicLong(0);

    public K addToStore(K object)
    {
        if(!store.containsKey(object.getId()))
            object.setId(pkey.incrementAndGet());
        store.put(object.getId(),object);
        return object;
    }

    public void removeFromStore(K object)
    {
        store.remove(object.getId());
    }

    public void removeFromStore(Long key)
    {
        store.remove(key);
    }
    public K getFromStore(Long key)
    {
        return store.get(key);
    }

    public boolean checkIfPresent(K object)
    {
        return store.containsKey(object.getId());
    }

    public boolean checkIfPresent(Long key)
    {
        return store.containsKey(key);
    }

    public List<K> getAll()
    {
        return new ArrayList<>(store.values());
    }

    public void clear()
    {
        store.clear();
    }

}

