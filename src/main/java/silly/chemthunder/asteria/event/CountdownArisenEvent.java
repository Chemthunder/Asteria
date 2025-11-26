package silly.chemthunder.asteria.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.fabricmc.fabric.api.client.rendering.v1.LayeredDrawerWrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.joml.Quaternionf;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;

public class CountdownArisenEvent implements HudLayerRegistrationCallback {
    @Override
    public void register(LayeredDrawerWrapper layeredDrawerWrapper) {
        layeredDrawerWrapper.attachLayerAfter(IdentifiedLayer.HOTBAR_AND_BARS, Asteria.id("arisen_countdown"), (context, tickCounter) -> {

            // countdown
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                if (ArisenPlayerComponent.KEY.get(player).arisenTicks > 0) {
                    MutableText mutableText = Text.literal("" + ArisenPlayerComponent.KEY.get(player).arisenTicks).formatted(Formatting.ITALIC);
                    context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,
                            mutableText,
                            context.getScaledWindowWidth() / 2 - 7,
                            3,
                            0x62ffae
                    );


                    // wiggles
                    int i = MinecraftClient.getInstance().textRenderer.getWidth(mutableText);
                    int j = (context.getScaledWindowWidth() - i) / 2;
                    int k = context.getScaledWindowHeight() - 59;
                    if (!MinecraftClient.getInstance().interactionManager.hasStatusBars()) {
                        k += 14;
                    }

                    float shouldOffset = (float) (Math.sin(MinecraftClient.getInstance().world.getTime() / 4f) * 2f);

                    Quaternionf quaternionf = new Quaternionf();
                    quaternionf.rotateXYZ(0f, 0, (float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
                    context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
                    context.getMatrices().translate(0, shouldOffset, -6);
                    context.getMatrices().translate(0, -shouldOffset, 6);
                    context.getMatrices().translate(0, -shouldOffset, 6);
                    quaternionf.rotationYXZ(0, 0f, -(float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
                    context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
                }
            }
        });
    }
}
