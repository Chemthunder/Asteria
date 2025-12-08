package silly.chemthunder.asteria.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

public class RitualMetalItem extends Item {
    public RitualMetalItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        EclipsedSkyWorldComponent.KEY.get(world).eclipseTicks = 0;
        EclipsedSkyWorldComponent.KEY.get(world).sync();

        return super.use(world, user, hand);
    }
}
