package silly.chemthunder.asteria.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(SkyRendering.class)
public abstract class SkyRenderingMixin implements AutoCloseable {
    @Unique private static final Identifier ECLIPSE_TEXTURE = Asteria.id("textures/environment/eclipse.png");

    @WrapOperation(method = "renderSun", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderLayer;getCelestial(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"))
    private RenderLayer sunSwap(Identifier texture, Operation<RenderLayer> original) {
        World world = MinecraftClient.getInstance().world;
        if (world != null && EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks > 0) {
            return original.call(ECLIPSE_TEXTURE);
        }
        return original.call(texture);
    }
}