package uk.debb.bat_uwu.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import uk.debb.bat_uwu.Constants;

@Mixin(Bat.class)
public abstract class MixinBat extends LivingEntity {
    public MixinBat(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private final String[] messages = {
            "Placeholder 1 % uwu",
            "Placeholder 2 % owo"
    };

    @Override
    public void die(DamageSource damageSource) {
        if (!this.isRemoved() && !this.dead && damageSource.getEntity() instanceof ServerPlayer serverPlayer) {
            String message = messages[(int) (Math.random() * messages.length)]
                    .replace("%", serverPlayer.getName().getString());
            Constants.server.getPlayerList().broadcastSystemMessage(Component.literal(message), false);
        }
        super.die(damageSource);
    }
}
