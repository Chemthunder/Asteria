package silly.chemthunder.asteria.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import silly.chemthunder.asteria.index.AsteriaBlockEntities;

import java.util.ArrayList;
import java.util.List;

public class EclipsedAltarBlockEntity extends BlockEntity {
    public EclipsedAltarBlockEntity(BlockPos pos, BlockState state) {
        super(AsteriaBlockEntities.ECLIPSED_ALTAR, pos, state);
    }
}
