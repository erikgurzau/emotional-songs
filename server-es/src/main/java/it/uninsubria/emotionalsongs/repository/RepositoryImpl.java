package it.uninsubria.emotionalsongs.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryImpl<T>{
    List<T> findAll();
    Optional<T> findById(Integer id);

}
