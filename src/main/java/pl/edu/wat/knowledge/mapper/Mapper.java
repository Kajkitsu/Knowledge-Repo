package pl.edu.wat.knowledge.mapper;

import pl.edu.wat.knowledge.entity.Entity;

public abstract class Mapper<T extends Entity, Z, X> {
    public T mapToEntity(X request) {
        T entity = createEntity(request);
        fillReflectionDataInEntity(entity, request);
        return entity;
    }

    protected abstract void fillReflectionDataInResponse(Z response, T entity);

    public Z mapToResponse(T entity) {
        Z response = createResponse(entity);
        fillReflectionDataInResponse(response, entity);
        return response;
    }

    protected abstract void fillReflectionDataInEntity(T entity, X response);

    protected abstract T createEntity(X request);

    protected abstract Z createResponse(T entity);
}
