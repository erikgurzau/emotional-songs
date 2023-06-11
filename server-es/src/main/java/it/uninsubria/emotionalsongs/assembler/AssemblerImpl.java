package it.uninsubria.emotionalsongs.assembler;

import java.util.List;

public interface AssemblerImpl<Entity, Model>  {

    Model toModel(Entity entity);
    List<Model> toModel(List<Entity> entityList);

}
