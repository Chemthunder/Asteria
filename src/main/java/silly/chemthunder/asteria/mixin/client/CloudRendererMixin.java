package silly.chemthunder.asteria.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.CloudRenderer;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(CloudRenderer.class)
public class CloudRendererMixin {
    @WrapMethod(method = "renderClouds")
    private void modifyColor(int color, CloudRenderMode cloudRenderMode, float cloudHeight, Vec3d cameraPos, float cloudsHeight, Operation<Void> original) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null && EclipsedSkyWorldComponent.KEY.get(client.world).eclipseTicks > 0) {
            original.call(0x121212, cloudRenderMode, cloudHeight, cameraPos, cloudHeight);
        } else {
            original.call(color, cloudRenderMode, cloudHeight, cameraPos, cloudHeight);
        }
    }
}