package cz.muni.fi.pa165.library.services;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@Service
public class MappingService {

    private final Mapper dozer;

    public MappingService(Mapper dozer) {
        this.dozer = dozer;
    }

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }
}

