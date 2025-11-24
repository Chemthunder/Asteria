package silly.chemthunder.asteria;

import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.asteria.index.AsteriaItems;

public class Asteria implements ModInitializer {
	public static final String MOD_ID = "asteria";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        AsteriaItems.index();

        ALib.registerModMenu(MOD_ID, 0xe348f3);
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            LOGGER.info("\uD83D\uDC00Mod initalized");
        }
	}
}