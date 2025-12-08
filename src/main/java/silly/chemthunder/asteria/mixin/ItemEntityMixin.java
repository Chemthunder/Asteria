package silly.chemthunder.asteria.mixin;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;
import silly.chemthunder.asteria.index.AsteriaBlocks;
import silly.chemthunder.asteria.index.AsteriaItems;
import silly.chemthunder.asteria.index.AsteriaSounds;

import java.util.Collection;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements Ownable {
    @Shadow
    public abstract ItemStack getStack();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        World world = getWorld();
        PlayerEntity player = (PlayerEntity) this.getOwner();
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

        if (state.isOf(AsteriaBlocks.ECLIPSED_ALTAR) && this.getStack().getItem().equals(AsteriaItems.ECLIPSED_EFFIGY)) {
            this.discard();
            if (player != null) {
                if (!getWorld().isNight()) {
                    MinecraftServer server = world.getServer();

                    EclipsedSkyWorldComponent eclipse = EclipsedSkyWorldComponent.KEY.get(world);
                    eclipse.eclipseTicks = 144000;
                    eclipse.sync();

                    if (server != null) {
                        Collection<ServerPlayerEntity> serverPlayerEntities = server.getPlayerManager().getPlayerList();

                        for (PlayerEntity player2 : serverPlayerEntities) {
                            player2.playSoundToPlayer(
                                    AsteriaSounds.ECLIPSE_TRIGGER,
                                    SoundCategory.PLAYERS,
                                    1,
                                    1
                            );

                            if (player instanceof ScreenShaker screenShaker) {
                                screenShaker.addScreenShake(1, 40);
                            }
                        }
                    }
                }
            }
        }
        super.onBlockCollision(state);
    }
}
