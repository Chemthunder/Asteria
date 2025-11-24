package silly.chemthunder.asteria.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(DimensionEffects.Overworld.class)
public abstract class OverworldDimensionEffectsMixin extends DimensionEffects {
    public OverworldDimensionEffectsMixin(float cloudsHeight, boolean alternateSkyColor, SkyType skyType, boolean brightenLighting, boolean darkened) {
        super(cloudsHeight, alternateSkyColor, skyType, brightenLighting, darkened);
    }

    @Inject(method = "adjustFogColor", at = @At("HEAD"), cancellable = true)
    private void newFog(Vec3d color, float sunHeight, CallbackInfoReturnable<Vec3d> cir) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world != null && EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks > 0) {
            cir.setReturnValue(Vec3d.of(Vec3i.ZERO));
        }
    }
}
