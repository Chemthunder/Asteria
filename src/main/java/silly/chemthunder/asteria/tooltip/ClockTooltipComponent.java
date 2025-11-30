package silly.chemthunder.asteria.tooltip;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import silly.chemthunder.asteria.Asteria;

public class ClockTooltipComponent implements TooltipComponent {
    private static final Identifier BACKGROUND = Asteria.id("textures/gui/sprites/hud/item/background.png");
    private static final Identifier ICON = Asteria.id("textures/gui/sprites/hud/item/icon.png");

    @Override
    public int getHeight(TextRenderer textRenderer) {
        return 24;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 24;
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, int width, int height, DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, BACKGROUND, x + 2, y + 2, 0, 0, 20, 20, 20, 20);
        context.drawTexture(RenderLayer::getGuiTextured, ICON, x + 4, y + 4, 0, 0, 16, 16, 16, 16);
    }
}