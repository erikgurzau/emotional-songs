package it.uninsubria.assembler;

import java.sql.ResultSet;
import java.util.List;

public interface AssemblerImpl<Model, Entity>  {

    Model toModel(Entity entity);
    List<Model> toModel(List<Entity> entityList);
    Entity toEntity(Model entity);
    List<Entity> toEntity(List<Model> modelList);
}
