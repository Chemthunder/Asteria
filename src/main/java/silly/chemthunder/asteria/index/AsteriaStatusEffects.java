package silly.chemthunder.asteria.index;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import silly.chemthunder.asteria.Asteria;
import silly.chemthunder.asteria.effect.RitualStarterEffect;
import silly.chemthunder.asteria.effect.SubordinateEffect;

public interface AsteriaStatusEffects {
    RegistryEntry<StatusEffect> SUPERIOR = create("superior", new RitualStarterEffect(StatusEffectCategory.NEUTRAL, 0xbd00d8));
    RegistryEntry<StatusEffect> SUBORDINATE = create("instability", new SubordinateEffect(StatusEffectCategory.NEUTRAL, 0xff64fd));

    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Asteria.id(name), statusEffect);
    }

    static void index() {
    }
}
