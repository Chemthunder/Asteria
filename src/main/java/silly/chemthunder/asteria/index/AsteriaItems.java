package silly.chemthunder.asteria.index;

import net.acoyt.acornlib.api.items.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.item.*;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface AsteriaItems {
    Item ECLIPSED_EFFIGY = create("eclipsed_effigy", EclipsedEffigyItem::new, new AcornItemSettings()
            .maxCount(1)
    );

    Item RITUALISTIC_BRACELET = create("ritualistic_bracelet", RitualisticBraceletItem::new, new AcornItemSettings()
            .maxCount(1)
    );

    Item CURSED_GOLD = create("cursed_gold", RitualMetalItem::new, new AcornItemSettings()
            .maxCount(16)
    );

    Item SWAMPY_FRUIT = create("swampy_fruit", Item::new, new AcornItemSettings()
    );

    Item MOLTEN_SWAMPY_GOLD_INGOT = create("molten_swampy_gold_ingot", RitualMetalItem::new, new AcornItemSettings()
            .maxCount(16)
    );

    Item SWAMPY_GOLD = create("swampy_gold", RitualMetalItem::new, new AcornItemSettings()
            .maxCount(16)
    );

    Item BYGONE_ERA = create("bygone_era", BygoneEraItem::new, new AcornItemSettings()
            .maxCount(1)
            .axe(Asteria.VEXED, 6.5f, -2.6f)
    );

    Item CLOCK_OF_VEXING = create("clock_of_vexing", ClockOfVexingItem::new, new AcornItemSettings()
            .maxCount(1)
    );

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Asteria.id(name)), factory, settings);
    }

    static void index() {
        modifyItemNameColor(ECLIPSED_EFFIGY, 0x62ffae);
        modifyItemNameColor(RITUALISTIC_BRACELET, 0x62ffae);
        modifyItemNameColor(BYGONE_ERA, 0x62ffae);
        modifyItemNameColor(CLOCK_OF_VEXING, 0x62ffae);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.TRIDENT, ECLIPSED_EFFIGY);
            entries.addAfter(ECLIPSED_EFFIGY, RITUALISTIC_BRACELET);
        });
    }
}
