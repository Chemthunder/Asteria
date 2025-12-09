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
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
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
