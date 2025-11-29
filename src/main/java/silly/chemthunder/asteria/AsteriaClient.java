package silly.chemthunder.asteria;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import silly.chemthunder.asteria.event.CountdownArisenEvent;
import silly.chemthunder.asteria.index.AsteriaBlocks;

public class AsteriaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudLayerRegistrationCallback.EVENT.register(new CountdownArisenEvent());

        AsteriaBlocks.clientIndex();
    }
}
