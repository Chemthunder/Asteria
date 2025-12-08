package silly.chemthunder.asteria.block.entity.render;

import com.nitron.nitrogen.render.RenderUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.block.entity.EclipsedAltarBlockEntity;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

public class EclipsedAltarBlockEntityRenderer implements BlockEntityRenderer<EclipsedAltarBlockEntity> {

    Identifier SILLY = Asteria.id("textures/entity/sun_book");
    @Override
    public void render(EclipsedAltarBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        assert entity.getWorld() != null;
        if (EclipsedSkyWorldComponent.KEY.get(entity.getWorld()).eclipseTicks > 0) {
            matrices.push();

            matrices.translate(-entity.getPos().getX() + 0.5, -entity.getPos().getY() + 0.5f, -entity.getPos().getZ() + 0.5);
            RenderUtils.renderTexturedSphere(
                    matrices,
                    vertexConsumers.getBuffer(RenderLayer.getEntitySolid(
                            Asteria.id(
                                    "textures/render/color.png"
                            )
                    )),
                    entity.getPos(),
                    5.0f,
                    350,
                    1
            );

            Quaternionf quaternionf = new Quaternionf();
            quaternionf.slerp(quaternionf, 1);
            matrices.multiply(quaternionf);



            matrices.pop();
        }

    }

}
