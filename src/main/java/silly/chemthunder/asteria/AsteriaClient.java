package silly.chemthunder.asteria;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import silly.chemthunder.asteria.block.entity.render.EclipsedAltarBlockEntityRenderer;
import silly.chemthunder.asteria.event.CountdownArisenEvent;
import silly.chemthunder.asteria.index.AsteriaBlockEntities;
import silly.chemthunder.asteria.index.AsteriaBlocks;
import silly.chemthunder.asteria.index.AsteriaEntities;
import silly.chemthunder.asteria.tooltip.ClockTooltipComponent;
import silly.chemthunder.asteria.tooltip.ClockTooltipData;

public class AsteriaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudLayerRegistrationCallback.EVENT.register(new CountdownArisenEvent());
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof ClockTooltipData) {
                return new ClockTooltipComponent();
            }
            return null;
        });

        AsteriaBlocks.clientIndex();
        AsteriaEntities.clientIndex();


        BlockEntityRendererFactories.register(AsteriaBlockEntities.ECLIPSED_ALTAR, context -> new EclipsedAltarBlockEntityRenderer());
    }
}
