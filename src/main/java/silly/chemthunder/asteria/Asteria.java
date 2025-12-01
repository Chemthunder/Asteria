package silly.chemthunder.asteria;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.asteria.compat.AsteriaConfig;
import silly.chemthunder.asteria.datagen.AsteriaItemTagProvider;
import silly.chemthunder.asteria.index.*;

public class Asteria implements ModInitializer {
	public static final String MOD_ID = "asteria";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ToolMaterial VEXED = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            1,
            1,
            1F,
            1,
            AsteriaItemTagProvider.VEXED
    );

	@Override
	public void onInitialize() {
        AsteriaItems.index();
        AsteriaSounds.index();
        AsteriaItemGroups.index();
        AsteriaBlocks.index();
        AsteriaEnchantments.index();
        AsteriaEntities.index();
        AsteriaBlockEntities.index();



        FabricLoader.getInstance().getModContainer(Asteria.MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(id("pear_teto_swampy_fruit"), container, Text.literal("pear teto"), ResourcePackActivationType.NORMAL);
        });

        ALib.registerModMenu(MOD_ID, 0x62ffae);
        MidnightConfig.init(MOD_ID, AsteriaConfig.class);
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            LOGGER.info("\uD83D\uDC00Mod initalized");
        }
	}
}