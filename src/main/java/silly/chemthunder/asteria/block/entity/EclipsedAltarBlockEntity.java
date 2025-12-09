package silly.chemthunder.asteria.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import silly.chemthunder.asteria.index.AsteriaBlockEntities;

public class EclipsedAltarBlockEntity extends BlockEntity {
    public EclipsedAltarBlockEntity(BlockPos pos, BlockState state) {
        super(AsteriaBlockEntities.ECLIPSED_ALTAR, pos, state);
    }
}
