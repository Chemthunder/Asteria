package silly.chemthunder.asteria.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Unique
    private static final Identifier TEST_OVERLAY = Asteria.id("textures/gui/sprites/hud/effect_overlay/test_overlay.png");
    @Shadow
    private ItemStack currentStack;

    @Shadow
    private int heldItemTooltipFade;

    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);


    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"))
    private void textWiggle(DrawContext context, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (ArisenPlayerComponent.KEY.get(player).arisenTicks > 0) {
                if (!this.currentStack.isEmpty()) {
                    MutableText mutableText = Text.empty().append(this.currentStack.getName()).formatted(this.currentStack.getRarity().getFormatting()).withColor(0x62ffae);
                    if (this.currentStack.contains(DataComponentTypes.CUSTOM_NAME)) {
                        mutableText.formatted(Formatting.ITALIC);
                    }

                    int i = this.getTextRenderer().getWidth(mutableText);
                    int j = (context.getScaledWindowWidth() - i) / 2;
                    int k = context.getScaledWindowHeight() - 59;
                    if (!this.client.interactionManager.hasStatusBars()) {
                        k += 14;
                    }

                    int l = (int)((float)this.heldItemTooltipFade * 256.0F / 10.0F);
                    if (l > 255) {
                        l = 255;
                    }

                    if (l > 0) {
                        context.drawTextWithBackground(this.getTextRenderer(), mutableText, j, k, i, ColorHelper.withAlpha(l, -1));
                    }
                }
            }
        }
    }

    @Inject(method = "renderMiscOverlays", at = @At("TAIL"))
    private void renderCustomOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (ArisenPlayerComponent.KEY.get(player).arisenTicks > 0) {
                this.renderOverlay(context, TEST_OVERLAY, 0.5f);
            }
        }
    }

}
