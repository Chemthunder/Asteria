package silly.chemthunder.asteria.mixin.client.eclipse;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "update", at = @At("RETURN"))
    private void eclipseWorldShake(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {

        EclipsedSkyWorldComponent eclipse = EclipsedSkyWorldComponent.KEY.get(focusedEntity.getWorld());
        if (eclipse.eclipseTicks > 0) {
            Camera camera = (Camera) (Object) this;

            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            int age = player.age;

            float amplitude = .0025f;
            float strength = 0.5f;

            float yawOffset = 0;
            float pitchOffset = 0;

            camera.setRotation(camera.getYaw() + yawOffset, camera.getPitch() + pitchOffset);
            camera.setPos(camera.getPos().add(0, Math.sin((age + tickDelta) * strength) / 2f * amplitude, Math.cos((age + tickDelta) * strength) * amplitude));
        }
    }
}
