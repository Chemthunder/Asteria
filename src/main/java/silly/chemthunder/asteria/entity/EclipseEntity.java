package silly.chemthunder.asteria.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

public class EclipseEntity extends LivingEntity {
    public EclipseEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Arm getMainArm() {
        return null;
    }
}
