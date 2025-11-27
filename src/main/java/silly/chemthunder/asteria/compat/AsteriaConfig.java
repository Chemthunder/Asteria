package silly.chemthunder.asteria.compat;

import eu.midnightdust.lib.config.MidnightConfig;

public class AsteriaConfig extends MidnightConfig {
    private static final String config = "config";

    @Entry(category = config)
    public static boolean test = true;
}
