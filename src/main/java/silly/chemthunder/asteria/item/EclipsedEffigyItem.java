package silly.chemthunder.asteria.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;

public class EclipsedEffigyItem extends Item {
    public EclipsedEffigyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ArisenPlayerComponent arisen = ArisenPlayerComponent.KEY.get(user);
        EclipsedSkyWorldComponent eclipse = EclipsedSkyWorldComponent.KEY.get(world);

        arisen.arisenTicks = 180;
        eclipse.eclipseTicks = 180;
        arisen.sync();
        eclipse.sync();
        user.getItemCooldownManager().set(this.getDefaultStack(), 60);
        return super.use(world, user, hand);
    }
}
