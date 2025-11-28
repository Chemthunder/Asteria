package silly.chemthunder.asteria.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getSkinTextures", at = @At("RETURN"), cancellable = true)
    private void customSkin(CallbackInfoReturnable<SkinTextures> cir) {
        SkinTextures defaultTextures = cir.getReturnValue();
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null) {
            if (defaultTextures != null && ArisenPlayerComponent.KEY.get(client.player).arisenTicks > 0) {
                cir.setReturnValue(new SkinTextures(
                        Asteria.id("textures/entity/arisen.png"),
                        defaultTextures.textureUrl(),
                        null,
                        null,
                        SkinTextures.Model.SLIM,
                        defaultTextures.secure()
                ));
            }
        }
    }
}
