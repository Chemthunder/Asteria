package silly.chemthunder.asteria.index;

import net.acoyt.acornlib.api.items.AcornItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.item.EclipsedEffigyItem;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface AsteriaItems {
    Item ECLIPSED_EFFIGY = create("eclipsed_effigy", EclipsedEffigyItem::new, new AcornItemSettings()
            .maxCount(1)
    );

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, Asteria.id(name)), factory, settings);
    }

    static void index() {
        //   modifyItemNameColor(AMARANTHINE_CLEAVER, 0x90403e);
        modifyItemNameColor(ECLIPSED_EFFIGY, 0xe348f3);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.TRIDENT, ECLIPSED_EFFIGY);
        });
    }
}
