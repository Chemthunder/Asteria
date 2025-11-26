package silly.chemthunder.asteria.mixin.client.eclipse;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(SkyRendering.class)
public abstract class SkyRenderingMixin implements AutoCloseable {
    @Unique private static final Identifier ECLIPSE_TEXTURE = Asteria.id("textures/environment/eclipse.png");
    @Unique private float rotationBlend = 0f;
    @Unique private float postEclipseBlend = 0f;
    @Unique private float timeAtFullRotation = 0f;
    @Unique private boolean inEclipseLastTick = false;
    @Unique private float startX = 0f;
    @Unique private float startZ = 0f;
    @Unique private final float ECLIPSE_TARGET_X = 25f; // target eclipse sun height
    @Unique private final float ECLIPSE_TARGET_Z = 45f;
    @Unique private static final float MAX_ROTATION_DEGREES = 45f;
    @Unique private static final float ROTATION_SPEED_DEGREES_PER_SECOND = 1f;

    @Inject(method = "renderSun", at = @At("HEAD"))
    private void pushRotateSun(float alpha, VertexConsumerProvider vcp, MatrixStack matrices, CallbackInfo ci) {
        matrices.push();

        World world = MinecraftClient.getInstance().world;
        boolean inEclipse = world != null && EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks > 0;
        float deltaSeconds = MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks();

        // Correct vanilla sun rotation (noon overhead)
        float normalRot = world != null ? ((world.getTimeOfDay() % 24000f) / 24000f) * 360f - 90f : 0f;

        if (inEclipse) {
            if (!inEclipseLastTick) {
                rotationBlend = 0f;
                startX = normalRot; // start from current sun rotation
                startZ = 0f;
            }

            rotationBlend += ROTATION_SPEED_DEGREES_PER_SECOND * deltaSeconds / MAX_ROTATION_DEGREES;
            if (rotationBlend > 1f) rotationBlend = 1f;

            float easedBlend = 0.5f * (1f - (float)Math.cos(Math.PI * rotationBlend));
            float interpX = startX + (ECLIPSE_TARGET_X - startX) * easedBlend;
            float interpZ = startZ + (ECLIPSE_TARGET_Z - startZ) * easedBlend;

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(interpX));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(interpZ));

            if (rotationBlend >= 1f) timeAtFullRotation += deltaSeconds;
            else timeAtFullRotation = 0f;

        } else {
            if (inEclipseLastTick) postEclipseBlend = 0f;

            if (postEclipseBlend < 1f) {
                postEclipseBlend += ROTATION_SPEED_DEGREES_PER_SECOND * deltaSeconds / MAX_ROTATION_DEGREES;
                if (postEclipseBlend > 1f) postEclipseBlend = 1f;

                float easedBlend = 0.5f * (1f - (float)Math.cos(Math.PI * postEclipseBlend));
                float start = ECLIPSE_TARGET_X;
                float interpX = start + (normalRot - start) * easedBlend;
                float interpZ = ECLIPSE_TARGET_Z + (0f - ECLIPSE_TARGET_Z) * easedBlend;

                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(interpX));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(interpZ));
            } else {
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(normalRot));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0f));
            }

            rotationBlend = 0f;
            timeAtFullRotation = 0f;
        }

        inEclipseLastTick = inEclipse;
    }

    @Inject(method = "renderSun", at = @At("TAIL"))
    private void popRotateSun(float alpha, VertexConsumerProvider vertexConsumers, MatrixStack matrices, CallbackInfo ci) {
        matrices.pop();
    }

    @WrapOperation(
            method = "renderSun",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayer;getCelestial(Lnet/minecraft/util/Identifier;)Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    private RenderLayer swapTextureAfterDelay(Identifier texture, Operation<RenderLayer> original) {
        if (timeAtFullRotation >= 1f) return original.call(ECLIPSE_TEXTURE);
        return original.call(texture);
    }

}