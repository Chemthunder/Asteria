package silly.chemthunder.asteria.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.asteria.Asteria;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AsteriaEntities {
    Map<EntityType<? extends Entity>, Identifier> ENTITIES = new LinkedHashMap<>();

  //  EntityType<EclipseEntity> TEST = createEntity("test", FabricEntityTypeBuilder.<EclipseEntity>create(SpawnGroup.MISC, EclipseEntity::new).disableSaving().dimensions(EntityDimensions.changing(5.0f, 0.2f)).build());

    private static <T extends EntityType<? extends Entity>> T createEntity(String name, T entity) {
        ENTITIES.put(entity, Identifier.of(Asteria.MOD_ID, name));
        return entity;
    }

    static void index() {
        ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
    }

    static void clientIndex() {
      //  EntityRendererRegistry.register(TEST, EmptyEntityRenderer::new);
    }
}
