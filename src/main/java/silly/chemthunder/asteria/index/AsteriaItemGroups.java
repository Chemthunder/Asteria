package silly.chemthunder.asteria.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import silly.chemthunder.asteria.Asteria;

public interface AsteriaItemGroups {
    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Asteria.id("asteria"));
    ItemGroup ASTERIA_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(AsteriaItems.ECLIPSED_EFFIGY))
            .displayName(Text.translatable("itemGroup.asteria").styled(style -> style.withColor(0x62ffae)))
            .build();

    static void index() {
        Registry.register(Registries.ITEM_GROUP, GROUP_KEY, ASTERIA_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(AsteriaItemGroups::addEntries);

    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(AsteriaItems.ECLIPSED_EFFIGY);
        itemGroup.add(AsteriaItems.RITUALISTIC_BRACELET);
        itemGroup.add(AsteriaBlocks.ECLIPSED_ALTAR);
        itemGroup.add(AsteriaItems.SWAMPY_FRUIT);
        itemGroup.add(AsteriaItems.SWAMPY_GOLD);
        itemGroup.add(AsteriaItems.MOLTEN_SWAMPY_GOLD_INGOT);
        itemGroup.add(AsteriaItems.BYGONE_ERA);
        itemGroup.add(AsteriaItems.CLOCK_OF_VEXING);
    }
}
