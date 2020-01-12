package com.zemoso.codezorro.taskSetService.helpers;

import com.zemoso.codezorro.taskSetService.model.Model;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class StorageProvider<K extends Model> {

    private Map<Long,K> store = new HashMap<>();
    private AtomicLong pkey = new AtomicLong(0);

    public K addToStore(K object)
    {
        if(!store.containsKey(object.getId())) {
            object.setId(pkey.incrementAndGet());}
        store.put(object.getId(), object);
        return object;

    }

    public void removeFromStore(K object)
    {
        store.remove(object.getId());
    }

    public void removeFromStore(Long key){
        store.remove(key);
    }

    public K getFromStore(Long key)
    {
        return store.get(key);
    }

    public boolean checkIfPresent(Long key)
    {
        return store.containsKey(key);
    }

    public void clear()
    {
        store.clear();
    }

    public List<K> getAll() {
        return new ArrayList<>(store.values());
    }
}