package silly.chemthunder.asteria.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;

public class RitualisticBraceletItem extends Item {
    public RitualisticBraceletItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ArisenPlayerComponent arisen = ArisenPlayerComponent.KEY.get(user);
        arisen.arisenTicks = 900;

        arisen.sync();
        user.getItemCooldownManager().set(this.getDefaultStack(), 90);
        return super.use(world, user, hand);
    }
}
