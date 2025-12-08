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
}
// bhkgrktuibgnuortb
