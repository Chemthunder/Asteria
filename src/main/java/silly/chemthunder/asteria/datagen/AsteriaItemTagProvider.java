package silly.chemthunder.asteria.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.index.AsteriaItems;

import java.util.concurrent.CompletableFuture;

public class AsteriaItemTagProvider extends FabricTagProvider<Item> {
    public AsteriaItemTagProvider(FabricDataOutput output, RegistryKey<? extends Registry<Item>> registryKey, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registryKey, registriesFuture);
    }
    public static final TagKey<Item> VEXED = TagKey.of(RegistryKeys.ITEM, Identifier.of(Asteria.MOD_ID, "vexed"));
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(VEXED)
                .add(AsteriaItems.SWAMPY_GOLD)
                .setReplace(true);
    }
}
