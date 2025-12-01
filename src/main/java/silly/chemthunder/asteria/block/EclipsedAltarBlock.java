package silly.chemthunder.asteria.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import silly.chemthunder.asteria.block.entity.EclipsedAltarBlockEntity;

public class EclipsedAltarBlock extends BlockWithEntity {
    public static final MapCodec<EclipsedAltarBlock> CODEC = createCodec(EclipsedAltarBlock::new);
    public EclipsedAltarBlock(Settings settings) {
        super(settings);
    }
    private static final VoxelShape SHAPE = Block.createColumnShape((double)16.0F, (double)0.0F, (double)11.0F);

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EclipsedAltarBlockEntity(pos, state);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof EclipsedAltarBlockEntity entity) {
            if (!stack.isOf(Blocks.AIR.asItem())) {
                entity.containedItems.add(stack);
                entity.markDirty();

                stack.decrementUnlessCreative(1, player);
            } else {
                player.sendMessage(Text.literal(":" + entity.containedItems.toString()), false);
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
    // remove items and sync with client / server

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        // items scatter when broken
        super.onBroken(world, pos, state);
    }
}
