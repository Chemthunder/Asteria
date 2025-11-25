package silly.chemthunder.asteria.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.SkyRendering;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(SkyRendering.class)
public abstract class SkyRenderingMixin {
    @Unique private static final Identifier ECLIPSE_TEXTURE = Asteria.id("textures/environment/eclipse.png");
    @Unique private float rotationBlend = 0f;
    @Unique private static final float MAX_ROTATION_DEGREES = 45f;
    @Unique private static final float ROTATION_SPEED_DEGREES_PER_SECOND = 1f;

    @Inject(method = "renderSun", at = @At("HEAD"))
    private void pushRotateSun(float alpha, VertexConsumerProvider vcp, MatrixStack matrices, CallbackInfo ci) {
        matrices.push();

        World world = MinecraftClient.getInstance().world;
        boolean inEclipse = world != null && EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks > 0;

        float deltaSeconds = MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks();

        float blendSpeed = ROTATION_SPEED_DEGREES_PER_SECOND / MAX_ROTATION_DEGREES * deltaSeconds;

        if (inEclipse) {
            rotationBlend += blendSpeed;
            if (rotationBlend > 1f) rotationBlend = 1f;
        } else {
            rotationBlend -= blendSpeed;
            if (rotationBlend < 0f) rotationBlend = 0f;
        }

        float easedBlend = (float)(0.5 * (1 - Math.cos(Math.PI * rotationBlend)));
        float sunRotation = MAX_ROTATION_DEGREES * easedBlend;

        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(sunRotation));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(sunRotation));
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
    private RenderLayer swapTextureInstant(Identifier texture, Operation<RenderLayer> original) {
        if (rotationBlend > 0.5f) {
            return original.call(ECLIPSE_TEXTURE);
        }
        return original.call(texture);
    }
}