package it.uninsubria.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryImpl<Entity>{
    List<Entity> findAll();
    Optional<Entity> findById(Integer id);
    Entity save(Entity entity);

}
