package silly.chemthunder.asteria.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import silly.chemthunder.asteria.api.ColorableItem;
import silly.chemthunder.asteria.tooltip.ClockTooltipData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClockOfVexingItem extends Item implements ColorableItem {
    public ClockOfVexingItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            MinecraftServer server = user.getServer();

            for (ServerPlayerEntity splayer : server.getPlayerManager().getPlayerList()) {
                if (splayer.getInventory().contains(this.getDefaultStack())) {
                    splayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000000000, 0, true, false, true));
                    // ! change this to arisen when its done
                }
            }
        }
        return super.use(world, user, hand);
    }

    // make this spawn a non culling particle that doesnt cull and shows thru walls, will make soon - everest
    // really unclear comment fuck me ig, when you hold it, spawn this particle on the client

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF62ffae;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF008741;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF00f2e1e;
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new ClockTooltipData());
    }
}
