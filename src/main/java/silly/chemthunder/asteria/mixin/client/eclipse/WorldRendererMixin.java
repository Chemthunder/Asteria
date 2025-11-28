package silly.chemthunder.asteria.mixin.client.eclipse;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin implements SynchronousResourceReloader, AutoCloseable {

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void disableClouds(FrameGraphBuilder frameGraphBuilder, CloudRenderMode mode, Vec3d cameraPos, float f, int color, float cloudHeight, CallbackInfo ci) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world != null && EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks > 0) {
            ci.cancel();
        }
    }
}

