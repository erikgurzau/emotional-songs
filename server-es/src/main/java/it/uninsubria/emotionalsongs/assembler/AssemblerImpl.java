package it.uninsubria.emotionalsongs.assembler;

import java.util.List;

public interface AssemblerImpl<Model, Entity>  {

    Model toModel(Entity entity);
    List<Model> toModel(List<Entity> entityList);

}
