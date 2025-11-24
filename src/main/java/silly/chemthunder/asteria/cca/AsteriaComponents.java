package silly.chemthunder.asteria.cca;


import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class AsteriaComponents implements EntityComponentInitializer, WorldComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, ArisenPlayerComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(ArisenPlayerComponent::new);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(EclipsedSkyWorldComponent.KEY, EclipsedSkyWorldComponent::new);
    }
}
