package silly.chemthunder.asteria.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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

    @ModifyReturnValue(method = "getMovementSpeed", at = @At("RETURN"))
    private float bonusMovementSpeed(float original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (ArisenPlayerComponent.KEY.get(player).arisenTicks > 0) {
            return original * 2.0f;
        }
        return original;
    }

    @ModifyReturnValue(method = "getHurtSound", at = @At("RETURN"))
    private SoundEvent customHitSound(SoundEvent original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (ArisenPlayerComponent.KEY.get(player).arisenTicks > 0) {
            return SoundEvents.BLOCK_AZALEA_LEAVES_BREAK;
        }
        return original;
    }

}
