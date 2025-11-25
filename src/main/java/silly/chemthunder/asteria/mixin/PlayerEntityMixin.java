package silly.chemthunder.asteria.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void ticker(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (EclipsedSkyWorldComponent.KEY.get(getWorld()).eclipseTicks > 0) {
            ArisenPlayerComponent arisen = ArisenPlayerComponent.KEY.get(player);
            arisen.arisenTicks = 180;
        }
    }
}
