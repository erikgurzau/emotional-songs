package it.uninsubria.assembler;

import java.sql.ResultSet;

public interface AssemblerImpl<Model, Entity>  {

    Model toModel(Entity entity);
    Model toModel(ResultSet resultSet);
    Entity toEntity(Model entity);
    Entity toEntity(ResultSet resultSet);
}
