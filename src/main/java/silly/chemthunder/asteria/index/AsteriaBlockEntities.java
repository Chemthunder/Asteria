package silly.chemthunder.asteria.index;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.block.entity.EclipsedAltarBlockEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AsteriaBlockEntities {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITIES = new LinkedHashMap<>();

    BlockEntityType<EclipsedAltarBlockEntity> ECLIPSED_ALTAR = create("eclipsed_altar", FabricBlockEntityTypeBuilder
            .create(EclipsedAltarBlockEntity::new)
            .addBlocks(AsteriaBlocks.ECLIPSED_ALTAR)
            .build());

    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> blockEntity) {
        BLOCK_ENTITIES.put(blockEntity, Asteria.id(name));
        return blockEntity;
    }

    static void index() {
        BLOCK_ENTITIES.keySet().forEach(blockEntity -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITIES.get(blockEntity), blockEntity);
        });
    }
}
