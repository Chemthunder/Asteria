package silly.chemthunder.asteria.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
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

    public List<ItemStack> containedItems = new ArrayList<>();

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.put("containedItems", ItemStack.CODEC.listOf(), this.containedItems);
        super.writeNbt(nbt, registries);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.containedItems.clear();
        this.containedItems.addAll(nbt.get("containedItems", ItemStack.CODEC.listOf()).orElse(List.of()));
        super.readNbt(nbt, registries);
    }
}
