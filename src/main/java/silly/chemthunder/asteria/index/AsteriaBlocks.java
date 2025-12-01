package silly.chemthunder.asteria.index;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.block.EclipsedAltarBlock;

import java.util.function.Function;

public interface AsteriaBlocks {
    Block ECLIPSED_ALTAR = createWithItem("eclipsed_altar", EclipsedAltarBlock::new, AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)
            .sounds(BlockSoundGroup.LODESTONE)
            .luminance((state -> 10))
    );

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Asteria.id(name)), factory, settings);
    }

    // Create and Register with an item, always
    static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        AsteriaItems.create(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Settings().useBlockPrefixedTranslationKey());

        return block;
    }

    static void index() {
        // a
    }

    static void clientIndex() {
       // BlockRenderLayerMap.INSTANCE.putBlock(TAINTED_GLASS, RenderLayer.getTranslucent());
    }
}
