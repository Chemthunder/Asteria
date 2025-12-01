package silly.chemthunder.asteria.block.entity.render;

import com.nitron.nitrogen.Nitrogen;
import com.nitron.nitrogen.render.RenderUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import silly.chemthunder.asteria.block.entity.EclipsedAltarBlockEntity;

import java.awt.*;

public class EclipsedAltarBlockEntityRenderer implements BlockEntityRenderer<EclipsedAltarBlockEntity> {

    @Override
    public void render(EclipsedAltarBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        // a
        matrices.pop();

        RenderUtils.renderSolidColorCube(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntitySolid(
                        Identifier.of(
                                Nitrogen.MOD_ID,
                                "textures/render/color.png"))),
                1,
                Vec3d.of(entity.getPos()),
                350
        );


        matrices.push();
    }
}
