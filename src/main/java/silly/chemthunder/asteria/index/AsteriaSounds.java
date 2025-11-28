package silly.chemthunder.asteria.index;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import silly.chemthunder.asteria.Asteria;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AsteriaSounds {
    Map<SoundEvent, Identifier> SOUNDS = new LinkedHashMap<>();

    SoundEvent ECLIPSE_TRIGGER = create("event.eclipse_trigger");

    private static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(Asteria.id(name));
        SOUNDS.put(soundEvent, Asteria.id(name));
        return soundEvent;
    }

    static void index() {
        SOUNDS.keySet().forEach(soundEvent -> {
            Registry.register(Registries.SOUND_EVENT, SOUNDS.get(soundEvent), soundEvent);
        });
    }
}
