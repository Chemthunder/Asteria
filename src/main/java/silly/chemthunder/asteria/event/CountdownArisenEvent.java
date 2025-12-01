package silly.chemthunder.asteria.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.fabricmc.fabric.api.client.rendering.v1.LayeredDrawerWrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.joml.Quaternionf;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;

public class CountdownArisenEvent implements HudLayerRegistrationCallback {
    private static int lastSeconds = -1;
    private static long lastChangeTime = 0;
    private static final long ANIM_DURATION = 300; // ms

    @Override
    public void register(LayeredDrawerWrapper layeredDrawerWrapper) {
        layeredDrawerWrapper.attachLayerAfter(
                IdentifiedLayer.HOTBAR_AND_BARS,
                Asteria.id("arisen_countdown"),
                this::renderCountdown
        );
    }

    private void renderCountdown(DrawContext context, RenderTickCounter tickCounter) {
    ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        int ticks = ArisenPlayerComponent.KEY.get(player).arisenTicks;
        if (ticks <= 0) return;

        int seconds = (int) Math.ceil(ticks / 20f);
        int prevSeconds = seconds + 1;

        if (seconds != lastSeconds) {
            lastSeconds = seconds;
            lastChangeTime = System.currentTimeMillis();
        }

        long now = System.currentTimeMillis();
        float t = Math.min(1f, (now - lastChangeTime) / (float) ANIM_DURATION);

        t = easeOutCubic(t);

        float offsetOld = -20f * t;
        float offsetNew = 20f * (1 - t);

        TextRenderer tr = MinecraftClient.getInstance().textRenderer;
        int x = context.getScaledWindowWidth() / 2 - 7;
        int y = 3;

        if (t < 1f) {
            MutableText prevText = Text.literal("" + prevSeconds).formatted(Formatting.ITALIC);
            context.drawTextWithShadow(tr, prevText, x, (int) (y + offsetOld), 0x62ffae);
        }

        MutableText curText = Text.literal("" + seconds).formatted(Formatting.ITALIC);
        context.drawTextWithShadow(tr, curText, x, (int) (y + offsetNew), 0x62ffae);

        int i = tr.getWidth(curText);
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
        quaternionf.rotationYXZ(0, 0f, -(float) (Math.cos(MinecraftClient.getInstance().world.getTime() / 8f) / 8f));
        context.getMatrices().multiply(quaternionf, j + i / 2f, k, 0);
    }

    private float linear(float t) {
        return t;
    }

    private float easeOutCubic(float t) {
        return 1 - (float) Math.pow(1 - t, 3);
    }

    private float easeInOutQuad(float t) {
        return (t < 0.5f)
                ? 2f * t * t
                : 1 - (float) Math.pow(-2f * t + 2f, 2) / 2f;
    }
}
