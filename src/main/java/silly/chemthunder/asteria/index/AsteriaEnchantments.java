package silly.chemthunder.asteria.index;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import silly.chemthunder.asteria.Asteria;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public interface AsteriaEnchantments {
    Map<ComponentType<?>, Identifier> ENCHANTMENT_EFFECTS = new LinkedHashMap<>();

    ComponentType<Unit> TEST = create("test", builder -> builder.codec(Unit.CODEC));

    private static <T> ComponentType<T> create(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        ComponentType<T> componentType = builderOperator.apply(ComponentType.builder()).build();
        ENCHANTMENT_EFFECTS.put(componentType, Asteria.id(id));
        return componentType;
    }

    static void index() {
        ENCHANTMENT_EFFECTS.keySet().forEach(effect -> {
            Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, ENCHANTMENT_EFFECTS.get(effect), effect);
        });
    }
}
