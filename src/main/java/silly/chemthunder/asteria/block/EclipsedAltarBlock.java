package silly.chemthunder.asteria.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
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
// nice comment
