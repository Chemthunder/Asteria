package silly.chemthunder.asteria.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import silly.chemthunder.asteria.Asteria;

public class ArisenPlayerComponent implements CommonTickingComponent, AutoSyncedComponent {
    public static final ComponentKey<ArisenPlayerComponent> KEY = ComponentRegistry.getOrCreate(Asteria.id("arisen"), ArisenPlayerComponent.class);
    private final PlayerEntity player;
    public int arisenTicks = 0;

    public ArisenPlayerComponent(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void tick() {
        if (arisenTicks > 0) {
            arisenTicks--;
            player.sendMessage(Text.literal("" + arisenTicks), true);
            player.setGlowing(true);
            if (arisenTicks == 0) {
                sync();
            }
        } else {
            player.setGlowing(false);
        }
    }

    public void sync() {
        KEY.sync(this.player);
    }

    @Override
    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.arisenTicks = nbt.getInt("arisenTicks", 0);
    }

    @Override
    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("arisenTicks", arisenTicks);
    }
}
