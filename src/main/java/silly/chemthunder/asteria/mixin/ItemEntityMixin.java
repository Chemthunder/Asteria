package silly.chemthunder.asteria.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import silly.chemthunder.asteria.index.AsteriaItems;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    @Shadow
    public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        if (state.isOf(Blocks.SOUL_CAMPFIRE) && this.getStack().getItem().equals(Items.GOLD_INGOT)) {
            if (getWorld() instanceof ServerWorld serverWorld) {
                dropStack(serverWorld, new ItemStack(AsteriaItems.CURSED_GOLD, this.getStack().getCount()));
                this.discard();
                serverWorld.spawnParticles(ParticleTypes.SOUL,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        2,
                        0,
                        0,
                        0,
                        0.2
                );
            }
        }
        super.onBlockCollision(state);
    }
}
