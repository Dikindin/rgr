package polyclinic.service;

import polyclinic.entity.AbstractEntity;
import java.util.List;

public interface Service<T extends AbstractEntity> {
    T read(Long id);
    List<T> read();
    void save(T entity);
    void delete(Long id);
}
