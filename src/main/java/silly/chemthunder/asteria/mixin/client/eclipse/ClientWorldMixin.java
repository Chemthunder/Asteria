package silly.chemthunder.asteria.mixin.client.eclipse;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.DataCache;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World implements DataCache.CacheContext<ClientWorld>{
    protected ClientWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DynamicRegistryManager registryManager, RegistryEntry<DimensionType> dimensionEntry, boolean isClient, boolean debugWorld, long seed, int maxChainedNeighborUpdates) {
        super(properties, registryRef, registryManager, dimensionEntry, isClient, debugWorld, seed, maxChainedNeighborUpdates);
    }

    @ModifyReturnValue(method = "getSkyColor", at = @At("RETURN"))
    private int newSky(int original) {
        ClientWorld world = MinecraftClient.getInstance().world;
        if (world != null && EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks > 0) {
            return 0x000000;
        }
        return original;
    }
}
