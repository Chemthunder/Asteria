package silly.chemthunder.asteria.cca;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import silly.chemthunder.asteria.Asteria;

public class EclipsedSkyWorldComponent implements CommonTickingComponent, AutoSyncedComponent {
    public static final ComponentKey<EclipsedSkyWorldComponent> KEY = ComponentRegistry.getOrCreate(Asteria.id("eclipse"), EclipsedSkyWorldComponent.class);
    private final World world;
    public int eclipseTicks = 0;

    public EclipsedSkyWorldComponent(World world) {
        this.world = world;
    }

    @Override
    public void tick() {
        if (eclipseTicks > 0) {
            eclipseTicks--;
            if (eclipseTicks == 0) {
                sync();
            }
        }
    }

    public void sync() {
        KEY.sync(this.world);
    }

    @Override
    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.eclipseTicks = nbt.getInt("eclipseTicks", 0);
    }

    @Override
    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("eclipseTicks", eclipseTicks);
    }
}
