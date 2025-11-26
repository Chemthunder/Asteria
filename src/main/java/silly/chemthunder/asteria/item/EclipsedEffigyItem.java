package silly.chemthunder.asteria.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;
import silly.chemthunder.asteria.index.AsteriaItems;
import silly.chemthunder.asteria.index.AsteriaSounds;

import java.util.Collection;
import java.util.List;

public class EclipsedEffigyItem extends Item {
    public EclipsedEffigyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        MinecraftServer server = world.getServer();

        EclipsedSkyWorldComponent eclipse = EclipsedSkyWorldComponent.KEY.get(world);
        eclipse.eclipseTicks = 180;

        eclipse.sync();
        if (!user.getGameMode().isCreative()) stack.decrement(1);

        Box area = new Box(user.getBlockPos()).expand(3);
        List<PlayerEntity> entities = world.getEntitiesByClass(PlayerEntity.class, area, entity -> true);

        for (PlayerEntity player : entities) {
            if (player.getInventory().contains(AsteriaItems.RITUALISTIC_BRACELET.getDefaultStack())) {
                ArisenPlayerComponent arisen = ArisenPlayerComponent.KEY.get(player);
                arisen.arisenTicks = 180;
                arisen.sync();
            }
        }

        if (server != null) {
            Collection<ServerPlayerEntity> serverPlayerEntities = server.getPlayerManager().getPlayerList();

            for (PlayerEntity player : serverPlayerEntities) {
                player.playSoundToPlayer(
                        AsteriaSounds.ECLIPSE_TRIGGER,
                        SoundCategory.PLAYERS,
                        1,
                        1
                );
            }
        }
        return super.use(world, user, hand);
    }
}
