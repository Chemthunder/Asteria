package silly.chemthunder.asteria.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import silly.chemthunder.asteria.cca.ArisenPlayerComponent;
import silly.chemthunder.asteria.cca.EclipsedSkyWorldComponent;
import silly.chemthunder.asteria.index.AsteriaStatusEffects;

import java.util.List;

public class EclipsedEffigyItem extends Item {
    public EclipsedEffigyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        ArisenPlayerComponent arisen = ArisenPlayerComponent.KEY.get(user);
        EclipsedSkyWorldComponent eclipse = EclipsedSkyWorldComponent.KEY.get(world);

        arisen.arisenTicks = 180;
        eclipse.eclipseTicks = 180;
        arisen.sync();
        eclipse.sync();
        stack.decrement(1);
        user.addStatusEffect(new StatusEffectInstance(AsteriaStatusEffects.SUPERIOR, 800));
        user.playSoundToPlayer(SoundEvents.ITEM_TRIDENT_HIT_GROUND, SoundCategory.MASTER, 1, 1);
        user.playSoundToPlayer(SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);

        Box area = new Box(user.getBlockPos()).expand(3);
        List<PlayerEntity> entities = world.getEntitiesByClass(PlayerEntity.class, area, entity -> true);

        for (PlayerEntity player : entities) {
            if (player != user) {
                player.addStatusEffect(new StatusEffectInstance(AsteriaStatusEffects.SUBORDINATE, 800));
                player.playSoundToPlayer(SoundEvents.ITEM_TRIDENT_HIT_GROUND, SoundCategory.MASTER, 1, 1);
                player.playSoundToPlayer(SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
            }
        }
        return super.use(world, user, hand);
    }
}
